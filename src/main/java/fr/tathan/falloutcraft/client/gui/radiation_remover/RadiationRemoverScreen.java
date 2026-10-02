/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
 *  net.minecraft.client.renderer.GameRenderer
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraft.world.item.TooltipFlag$Default
 */
package fr.tathan.falloutcraft.client.gui.radiation_remover;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import fr.tathan.falloutcraft.client.gui.radiation_remover.RadiationRemoverMenu;
import fr.tathan.falloutcraft.client.gui.FluidTankRenderer;
import fr.tathan.falloutcraft.common.util.MouseUtil;
import java.util.Optional;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.TooltipFlag;

public class RadiationRemoverScreen
extends AbstractContainerScreen<RadiationRemoverMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("falloutcraft", "textures/gui/radiation_remover.png");
    private FluidTankRenderer renderer;
    private FluidTankRenderer renderer2;

    public RadiationRemoverScreen(RadiationRemoverMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    protected void init() {
        super.init();
        this.assignFluidRenderer();
    }

    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        this.renderWaterAreaTooltips(pPoseStack, pMouseX, pMouseY, x, y);
        this.renderRadiatedWaterAreaTooltips(pPoseStack, pMouseX, pMouseY, x, y);
    }

    private void assignFluidRenderer() {
        this.renderer = new FluidTankRenderer(64000L, true, 16, 61);
        this.renderer2 = new FluidTankRenderer(64000L, true, 16, 61);
    }

    private void renderWaterAreaTooltips(PoseStack pPoseStack, int pMouseX, int pMouseY, int x, int y) {
        if (this.isMouseAboveArea(pMouseX, pMouseY, x, y, 55, 15)) {
            this.renderTooltip(pPoseStack, this.renderer.getTooltip(((RadiationRemoverMenu)this.menu).getWaterStack(), (TooltipFlag)TooltipFlag.Default.NORMAL), Optional.empty(), pMouseX - x, pMouseY - y);
        }
    }

    private void renderRadiatedWaterAreaTooltips(PoseStack pPoseStack, int pMouseX, int pMouseY, int x, int y) {
        if (this.isMouseAboveArea(pMouseX, pMouseY, x, y, 100, 15)) {
            this.renderTooltip(pPoseStack, this.renderer.getTooltip(((RadiationRemoverMenu)this.menu).getRadiatedWaterStack(), (TooltipFlag)TooltipFlag.Default.NORMAL), Optional.empty(), pMouseX - x, pMouseY - y);
        }
    }

    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        RenderSystem.setShaderTexture((int)0, (ResourceLocation)TEXTURE);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        this.blit(pPoseStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
        this.renderer.render(pPoseStack, x + 55, y + 15, ((RadiationRemoverMenu)this.menu).getWaterStack());
        this.renderer2.render(pPoseStack, x + 100, y + 15, ((RadiationRemoverMenu)this.menu).getRadiatedWaterStack());
    }

    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        this.renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        this.renderTooltip(pPoseStack, mouseX, mouseY);
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, this.renderer.getWidth(), this.renderer.getHeight());
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }
}

