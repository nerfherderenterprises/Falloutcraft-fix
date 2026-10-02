/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.shaders.FogShape
 *  com.mojang.blaze3d.systems.RenderSystem
 *  com.mojang.math.Vector3f
 *  net.minecraft.client.Camera
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.renderer.FogRenderer$FogMode
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions
 *  net.minecraftforge.fluids.FluidType
 *  net.minecraftforge.fluids.FluidType$Properties
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package fr.tathan.falloutcraft.common.fluid;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Vector3f;
import java.util.function.Consumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BaseFluidType
extends FluidType {
    private final ResourceLocation stillTexture;
    private final ResourceLocation flowingTexture;
    private final ResourceLocation overlayTexture;
    private final int tintColor;
    private final Vector3f fogColor;

    public BaseFluidType(ResourceLocation stillTexture, ResourceLocation flowingTexture, ResourceLocation overlayTexture, int tintColor, Vector3f fogColor, FluidType.Properties properties) {
        super(properties);
        this.stillTexture = stillTexture;
        this.flowingTexture = flowingTexture;
        this.overlayTexture = overlayTexture;
        this.tintColor = tintColor;
        this.fogColor = fogColor;
    }

    public ResourceLocation getStillTexture() {
        return this.stillTexture;
    }

    public ResourceLocation getFlowingTexture() {
        return this.flowingTexture;
    }

    public int getTintColor() {
        return this.tintColor;
    }

    public ResourceLocation getOverlayTexture() {
        return this.overlayTexture;
    }

    public Vector3f getFogColor() {
        return this.fogColor;
    }

    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions(){

            public ResourceLocation getStillTexture() {
                return BaseFluidType.this.stillTexture;
            }

            public ResourceLocation getFlowingTexture() {
                return BaseFluidType.this.flowingTexture;
            }

            @Nullable
            public ResourceLocation getOverlayTexture() {
                return BaseFluidType.this.overlayTexture;
            }

            public int getTintColor() {
                return BaseFluidType.this.tintColor;
            }

            @NotNull
            public Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
                return BaseFluidType.this.fogColor;
            }

            public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
                RenderSystem.setShaderFogStart((float)1.0f);
                RenderSystem.setShaderFogEnd((float)6.0f);
            }
        });
    }
}

