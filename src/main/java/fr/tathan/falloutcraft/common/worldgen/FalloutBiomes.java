/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Registry
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.biome.Biome
 */
package fr.tathan.falloutcraft.common.worldgen;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class FalloutBiomes {
    public static final ResourceKey<Biome> RADIOACTIVE_PLAINS = FalloutBiomes.register("radioactive_plains");
    public static final ResourceKey<Biome> RADIOACTIVE_FOREST = FalloutBiomes.register("radioactive_forest");
    public static final ResourceKey<Biome> POISONED_JUNGLE = FalloutBiomes.register("poisoned_jungle");
    public static final ResourceKey<Biome> ASH_LAND = FalloutBiomes.register("ash_land");

    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create((ResourceKey)Registry.BIOME_REGISTRY, (ResourceLocation)new ResourceLocation("falloutcraft", name));
    }
}

