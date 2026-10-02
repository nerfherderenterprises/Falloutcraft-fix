/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Vector3f
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.LightTexture
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.block.model.ItemTransforms$TransformType
 *  net.minecraft.client.renderer.blockentity.BlockEntityRenderer
 *  net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider$Context
 *  net.minecraft.client.renderer.entity.ItemRenderer
 *  net.minecraft.client.renderer.texture.OverlayTexture
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LightLayer
 *  net.minecraft.world.level.block.state.properties.Property
 */
package fr.tathan.falloutcraft.common.blocks.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import fr.tathan.falloutcraft.common.blocks.NukaColaMachine;
import fr.tathan.falloutcraft.common.blocks.entity.RadiationRemoverBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.properties.Property;

public class RadiationRemoverBlockEntityRenderer
implements BlockEntityRenderer<RadiationRemoverBlockEntity> {
    public RadiationRemoverBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    public void render(RadiationRemoverBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = pBlockEntity.getRenderStack();
        pPoseStack.pushPose();
        pPoseStack.translate(0.5, (double)0.65f, 0.5);
        pPoseStack.scale(0.25f, 0.25f, 0.25f);
        pPoseStack.mulPose(Vector3f.XP.rotationDegrees(90.0f));
        switch ((Direction)pBlockEntity.getBlockState().getValue((Property)NukaColaMachine.FACING)) {
            case NORTH: {
                pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(0.0f));
                break;
            }
            case EAST: {
                pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(90.0f));
                break;
            }
            case SOUTH: {
                pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0f));
                break;
            }
            case WEST: {
                pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(270.0f));
            }
        }
        itemRenderer.renderStatic(itemStack, ItemTransforms.TransformType.GUI, this.getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos()), OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, 1);
        pPoseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack((int)bLight, (int)sLight);
    }
}

