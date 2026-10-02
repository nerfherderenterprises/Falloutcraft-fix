/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  net.minecraft.core.Registry
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.biome.Biomes
 *  net.minecraft.world.level.biome.Climate$Parameter
 *  net.minecraft.world.level.biome.Climate$ParameterPoint
 *  terrablender.api.ParameterUtils$Continentalness
 *  terrablender.api.ParameterUtils$Depth
 *  terrablender.api.ParameterUtils$Erosion
 *  terrablender.api.ParameterUtils$Humidity
 *  terrablender.api.ParameterUtils$ParameterPointListBuilder
 *  terrablender.api.ParameterUtils$Temperature
 *  terrablender.api.ParameterUtils$Weirdness
 *  terrablender.api.Region
 *  terrablender.api.RegionType
 */
package fr.tathan.falloutcraft.common.worldgen;

import com.mojang.datafixers.util.Pair;
import fr.tathan.falloutcraft.common.worldgen.FalloutBiomes;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;

public class FalloutRegion
extends Region {
    public FalloutRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            List PlainsPoints = new ParameterUtils.ParameterPointListBuilder().temperature(new ParameterUtils.Temperature[]{ParameterUtils.Temperature.COOL}).humidity(new ParameterUtils.Humidity[]{ParameterUtils.Humidity.NEUTRAL}).continentalness(new Climate.Parameter[]{ParameterUtils.Continentalness.span((ParameterUtils.Continentalness)ParameterUtils.Continentalness.MID_INLAND, (ParameterUtils.Continentalness)ParameterUtils.Continentalness.FAR_INLAND)}).erosion(new ParameterUtils.Erosion[]{ParameterUtils.Erosion.EROSION_4}).depth(new ParameterUtils.Depth[]{ParameterUtils.Depth.SURFACE}).weirdness(new ParameterUtils.Weirdness[]{ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.PEAK_VARIANT, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_DESCENDING}).build();
            List ForestPoints = new ParameterUtils.ParameterPointListBuilder().temperature(new ParameterUtils.Temperature[]{ParameterUtils.Temperature.COOL}).humidity(new ParameterUtils.Humidity[]{ParameterUtils.Humidity.NEUTRAL}).continentalness(new Climate.Parameter[]{ParameterUtils.Continentalness.span((ParameterUtils.Continentalness)ParameterUtils.Continentalness.MID_INLAND, (ParameterUtils.Continentalness)ParameterUtils.Continentalness.FAR_INLAND)}).erosion(new ParameterUtils.Erosion[]{ParameterUtils.Erosion.EROSION_0, ParameterUtils.Erosion.EROSION_1}).depth(new ParameterUtils.Depth[]{ParameterUtils.Depth.SURFACE}).weirdness(new ParameterUtils.Weirdness[]{ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.PEAK_VARIANT, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_DESCENDING}).build();
            List JunglePoints = new ParameterUtils.ParameterPointListBuilder().temperature(new ParameterUtils.Temperature[]{ParameterUtils.Temperature.COOL}).humidity(new ParameterUtils.Humidity[]{ParameterUtils.Humidity.HUMID}).continentalness(new Climate.Parameter[]{ParameterUtils.Continentalness.span((ParameterUtils.Continentalness)ParameterUtils.Continentalness.MID_INLAND, (ParameterUtils.Continentalness)ParameterUtils.Continentalness.FAR_INLAND)}).erosion(new ParameterUtils.Erosion[]{ParameterUtils.Erosion.EROSION_0, ParameterUtils.Erosion.EROSION_1}).depth(new ParameterUtils.Depth[]{ParameterUtils.Depth.SURFACE}).weirdness(new ParameterUtils.Weirdness[]{ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_ASCENDING, ParameterUtils.Weirdness.PEAK_VARIANT, ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_DESCENDING}).build();
            ForestPoints.forEach(point -> builder.replaceBiome(Biomes.FOREST, FalloutBiomes.RADIOACTIVE_FOREST));
            PlainsPoints.forEach(point -> builder.replaceBiome(Biomes.PLAINS, FalloutBiomes.RADIOACTIVE_PLAINS));
            JunglePoints.forEach(point -> builder.replaceBiome(Biomes.JUNGLE, FalloutBiomes.POISONED_JUNGLE));
            PlainsPoints.forEach(point -> builder.replaceBiome(Biomes.DESERT, FalloutBiomes.ASH_LAND));
        });
    }
}

