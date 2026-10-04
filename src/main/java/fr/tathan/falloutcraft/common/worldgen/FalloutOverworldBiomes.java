/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.Holder
 *  net.minecraft.data.worldgen.BiomeDefaultFeatures
 *  net.minecraft.sounds.Music
 *  net.minecraft.sounds.Musics
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraft.util.Mth
 *  net.minecraft.world.level.biome.AmbientMoodSettings
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.biome.Biome$BiomeBuilder
 *  net.minecraft.world.level.biome.Biome$Precipitation
 *  net.minecraft.world.level.biome.BiomeGenerationSettings$Builder
 *  net.minecraft.world.level.biome.BiomeSpecialEffects$Builder
 *  net.minecraft.world.level.biome.MobSpawnSettings$Builder
 *  net.minecraft.world.level.levelgen.GenerationStep$Decoration
 */
package fr.tathan.falloutcraft.common.worldgen;

import fr.tathan.falloutcraft.common.registries.SoundsRegistry;
import fr.tathan.falloutcraft.common.worldgen.features.FalloutPlacedFeatures;
import javax.annotation.Nullable;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class FalloutOverworldBiomes {
    private static final Music APOCALYPTIC_MUSIC = Musics.createGameMusic((SoundEvent)((SoundEvent)SoundsRegistry.APOCALYSPE_MUSIC_1.get()));

    protected static int calculateSkyColor(float color) {
        float $$1 = color / 3.0f;
        $$1 = Mth.clamp((float)$$1, (float)-1.0f, (float)1.0f);
        return Mth.hsvToRgb((float)(0.62222224f - $$1 * 0.05f), (float)(0.5f + $$1 * 0.1f), (float)1.0f);
    }

    private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music) {
        return FalloutOverworldBiomes.biome(precipitation, temperature, downfall, 4159204, 329011, spawnBuilder, biomeBuilder, music);
    }

    private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor, MobSpawnSettings.Builder spawnBuilder, BiomeGenerationSettings.Builder biomeBuilder, @Nullable Music music) {
        return new Biome.BiomeBuilder().precipitation(precipitation).temperature(temperature).downfall(downfall).specialEffects(new BiomeSpecialEffects.Builder().waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(FalloutOverworldBiomes.calculateSkyColor(temperature)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music).build()).mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes((BiomeGenerationSettings.Builder)builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations((BiomeGenerationSettings.Builder)builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom((BiomeGenerationSettings.Builder)builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety((BiomeGenerationSettings.Builder)builder);
        BiomeDefaultFeatures.addDefaultSprings((BiomeGenerationSettings.Builder)builder);
        BiomeDefaultFeatures.addSurfaceFreezing((BiomeGenerationSettings.Builder)builder);
    }

    public static Biome radioactivePlains() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.plainsSpawns((MobSpawnSettings.Builder)spawnBuilder);
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        BiomeDefaultFeatures.addFossilDecoration((BiomeGenerationSettings.Builder)biomeBuilder);
        FalloutOverworldBiomes.globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres((BiomeGenerationSettings.Builder)biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks((BiomeGenerationSettings.Builder)biomeBuilder);
        BiomeDefaultFeatures.addDefaultGrass((BiomeGenerationSettings.Builder)biomeBuilder);
        BiomeDefaultFeatures.addDesertVegetation((BiomeGenerationSettings.Builder)biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms((BiomeGenerationSettings.Builder)biomeBuilder);
        BiomeDefaultFeatures.addDesertExtraVegetation((BiomeGenerationSettings.Builder)biomeBuilder);
        BiomeDefaultFeatures.addDesertExtraDecoration((BiomeGenerationSettings.Builder)biomeBuilder);
        BiomeDefaultFeatures.addAncientDebris((BiomeGenerationSettings.Builder)biomeBuilder);
        FalloutOverworldBiomes.addLakes(biomeBuilder);
        FalloutOverworldBiomes.calculateSkyColor(6415715.0f);
        return FalloutOverworldBiomes.biome(Biome.Precipitation.RAIN, 2.0f, 0.0f, 6415715, 6415715, spawnBuilder, biomeBuilder, APOCALYPTIC_MUSIC);
    }

    public static Biome radioactiveForest() {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns((MobSpawnSettings.Builder)spawnBuilder);
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        BiomeDefaultFeatures.addFossilDecoration((BiomeGenerationSettings.Builder)biomeBuilder);
        FalloutOverworldBiomes.globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres((BiomeGenerationSettings.Builder)biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks((BiomeGenerationSettings.Builder)biomeBuilder);
        BiomeDefaultFeatures.addDefaultGrass((BiomeGenerationSettings.Builder)biomeBuilder);
        BiomeDefaultFeatures.addDesertVegetation((BiomeGenerationSettings.Builder)biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms((BiomeGenerationSettings.Builder)biomeBuilder);
        BiomeDefaultFeatures.addDesertExtraVegetation((BiomeGenerationSettings.Builder)biomeBuilder);
        BiomeDefaultFeatures.addDesertExtraDecoration((BiomeGenerationSettings.Builder)biomeBuilder);
        BiomeDefaultFeatures.addAncientDebris((BiomeGenerationSettings.Builder)biomeBuilder);
        FalloutOverworldBiomes.addLakes(biomeBuilder);
        return FalloutOverworldBiomes.biome(Biome.Precipitation.RAIN, 2.0f, 0.0f, 6415715, 6415715, spawnBuilder, biomeBuilder, APOCALYPTIC_MUSIC);
    }

    public static Biome poisonedJungle() {
        MobSpawnSettings.Builder mobspawnsetting = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.baseJungleSpawns((MobSpawnSettings.Builder)mobspawnsetting);
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
        FalloutOverworldBiomes.globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres((BiomeGenerationSettings.Builder)biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks((BiomeGenerationSettings.Builder)biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms((BiomeGenerationSettings.Builder)biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation((BiomeGenerationSettings.Builder)biomeBuilder);
        BiomeDefaultFeatures.addJungleVines((BiomeGenerationSettings.Builder)biomeBuilder);
        FalloutOverworldBiomes.jungleFeatures(biomeBuilder);
        FalloutOverworldBiomes.addLakes(biomeBuilder);
        return FalloutOverworldBiomes.biome(Biome.Precipitation.RAIN, 2.0f, 0.0f, 6415715, 6415715, mobspawnsetting, biomeBuilder, APOCALYPTIC_MUSIC);
    }

    public static Biome ashLand() {
        MobSpawnSettings.Builder mobspawnsetting = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.baseJungleSpawns((MobSpawnSettings.Builder)mobspawnsetting);
        BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder();
        FalloutOverworldBiomes.globalOverworldGeneration(biomegenerationsettings$builder);
        BiomeDefaultFeatures.addDefaultOres((BiomeGenerationSettings.Builder)biomegenerationsettings$builder);
        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.LAKES, (Holder)FalloutPlacedFeatures.RADIATED_WATER_LAKE_ASH.getHolder().get());
        biomegenerationsettings$builder.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, (Holder)FalloutPlacedFeatures.ASH_TOP_LAYER.getHolder().get());
        return FalloutOverworldBiomes.biome(Biome.Precipitation.RAIN, 2.0f, 0.0f, 6415715, 6415715, mobspawnsetting, biomegenerationsettings$builder, APOCALYPTIC_MUSIC);
    }

    public static void addLakes(BiomeGenerationSettings.Builder biomeBuilder) {
        biomeBuilder.addFeature(GenerationStep.Decoration.LAKES, (Holder)FalloutPlacedFeatures.QUICKSAND_LAKE.getHolder().get());
        biomeBuilder.addFeature(GenerationStep.Decoration.LAKES, (Holder)FalloutPlacedFeatures.QUICKDIRT_LAKE.getHolder().get());
        biomeBuilder.addFeature(GenerationStep.Decoration.LAKES, (Holder)FalloutPlacedFeatures.RADIATED_WATER_LAKE.getHolder().get());
    }

    public static void jungleFeatures(BiomeGenerationSettings.Builder biomeBuilder) {
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, (Holder)FalloutPlacedFeatures.POISONED_JUNGLE_TREE_PLACED_KEY.getHolder().get());
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, (Holder)FalloutPlacedFeatures.POISONED_GRASS_PATCH.getHolder().get());
        biomeBuilder.addFeature(GenerationStep.Decoration.TOP_LAYER_MODIFICATION, (Holder)FalloutPlacedFeatures.ASH_TOP_LAYER.getHolder().get());
    }
}

