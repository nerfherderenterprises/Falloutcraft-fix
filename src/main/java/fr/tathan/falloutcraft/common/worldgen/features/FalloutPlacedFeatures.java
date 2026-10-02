/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Holder
 *  net.minecraft.core.Registry
 *  net.minecraft.data.worldgen.placement.PlacementUtils
 *  net.minecraft.data.worldgen.placement.VegetationPlacements
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.levelgen.placement.BiomeFilter
 *  net.minecraft.world.level.levelgen.placement.CountPlacement
 *  net.minecraft.world.level.levelgen.placement.InSquarePlacement
 *  net.minecraft.world.level.levelgen.placement.PlacedFeature
 *  net.minecraft.world.level.levelgen.placement.PlacementModifier
 *  net.minecraft.world.level.levelgen.placement.RarityFilter
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.RegistryObject
 */
package fr.tathan.falloutcraft.common.worldgen.features;

import fr.tathan.falloutcraft.common.registries.BlocksRegistry;
import fr.tathan.falloutcraft.common.worldgen.features.FalloutConfiguredFeatures;
import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class FalloutPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create((ResourceKey)Registry.PLACED_FEATURE_REGISTRY, (String)"falloutcraft");
    public static final RegistryObject<PlacedFeature> IRRADIATED_OAK_CHECKED = PLACED_FEATURES.register("irradiated_oak_checked", () -> new PlacedFeature((Holder)FalloutConfiguredFeatures.IRRADIATED_OAK.getHolder().get(), List.of(PlacementUtils.filteredByBlockSurvival((Block)((Block)BlocksRegistry.IRRADIATED_OAK_SAPLING.get())))));
    public static final RegistryObject<PlacedFeature> IRRADIATED_OAK_PLACED = PLACED_FEATURES.register("irradiated_oak_placed", () -> new PlacedFeature((Holder)FalloutConfiguredFeatures.IRRADIATED_OAK_SPAWN.getHolder().get(), VegetationPlacements.treePlacement((PlacementModifier)PlacementUtils.countExtra((int)3, (float)0.1f, (int)2))));
    public static final RegistryObject<PlacedFeature> POISONED_JUNGLE_TREE_CHECKED = PLACED_FEATURES.register("poisoned_jungle_tree_checked", () -> new PlacedFeature((Holder)FalloutConfiguredFeatures.POISONED_JUNGLE_TREE.getHolder().get(), List.of(PlacementUtils.filteredByBlockSurvival((Block)((Block)BlocksRegistry.POISONED_JUNGLE_SAPLING.get())))));
    public static final RegistryObject<PlacedFeature> POISONED_JUNGLE_TREE_PLACED_KEY = PLACED_FEATURES.register("poisoned_jungle_tree_placed", () -> new PlacedFeature((Holder)FalloutConfiguredFeatures.POISONED_JUNGLE_TREE_SPAWN_KEY.getHolder().get(), VegetationPlacements.treePlacement((PlacementModifier)PlacementUtils.countExtra((int)3, (float)0.1f, (int)2))));
    public static final RegistryObject<PlacedFeature> BURNT_OAK_CHECKED = PLACED_FEATURES.register("burnt_oak_checked", () -> new PlacedFeature((Holder)FalloutConfiguredFeatures.BURNT_OAK.getHolder().get(), List.of(PlacementUtils.filteredByBlockSurvival((Block)((Block)BlocksRegistry.BURNT_OAK_SAPLING.get())))));
    public static final RegistryObject<PlacedFeature> BURNT_OAK_PLACED = PLACED_FEATURES.register("burnt_oak_placed", () -> new PlacedFeature((Holder)FalloutConfiguredFeatures.BURNT_OAK_SPAWN.getHolder().get(), VegetationPlacements.treePlacement((PlacementModifier)PlacementUtils.countExtra((int)3, (float)0.1f, (int)2))));
    public static final RegistryObject<PlacedFeature> RADIOACTIVA_PLACED = PLACED_FEATURES.register("radioactiva_placed", () -> new PlacedFeature((Holder)FalloutConfiguredFeatures.RADIOACTIVA.getHolder().get(), List.of(RarityFilter.onAverageOnceEvery((int)16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> RADIATED_WATER_LAKE = PLACED_FEATURES.register("radiated_water_lake", () -> new PlacedFeature((Holder)FalloutConfiguredFeatures.RADIATED_WATER_LAKE.getHolder().get(), List.of(RarityFilter.onAverageOnceEvery((int)70), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> RADIATED_WATER_LAKE_ASH = PLACED_FEATURES.register("radiated_water_lake_ash", () -> new PlacedFeature((Holder)FalloutConfiguredFeatures.RADIATED_WATER_LAKE_ASH.getHolder().get(), List.of(RarityFilter.onAverageOnceEvery((int)70), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> QUICKSAND_LAKE = PLACED_FEATURES.register("quicksand_lake", () -> new PlacedFeature((Holder)FalloutConfiguredFeatures.RADIATED_WATER_LAKE.getHolder().get(), List.of(RarityFilter.onAverageOnceEvery((int)70), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> QUICKDIRT_LAKE = PLACED_FEATURES.register("quickdirt_lake", () -> new PlacedFeature((Holder)FalloutConfiguredFeatures.RADIATED_WATER_LAKE.getHolder().get(), List.of(RarityFilter.onAverageOnceEvery((int)70), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())));
    public static final RegistryObject<PlacedFeature> POISONED_GRASS_PATCH = PLACED_FEATURES.register("poisoned_grass_patch_placed", () -> new PlacedFeature((Holder)FalloutConfiguredFeatures.POISONED_GRASS_PATCH.getHolder().get(), VegetationPlacements.worldSurfaceSquaredWithCount((int)25)));
    public static final RegistryObject<PlacedFeature> ASH_TOP_LAYER = PLACED_FEATURES.register("ash_top_layer", () -> new PlacedFeature((Holder)FalloutConfiguredFeatures.ASH_TOP_LAYER.getHolder().get(), List.of(BiomeFilter.biome())));

    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return FalloutPlacedFeatures.orePlacement((PlacementModifier)CountPlacement.of((int)p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return FalloutPlacedFeatures.orePlacement((PlacementModifier)RarityFilter.onAverageOnceEvery((int)p_195350_), p_195351_);
    }
}

