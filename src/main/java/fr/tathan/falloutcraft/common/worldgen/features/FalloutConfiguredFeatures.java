/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Holder
 *  net.minecraft.core.Registry
 *  net.minecraft.core.Vec3i
 *  net.minecraft.data.worldgen.placement.PlacementUtils
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.util.random.SimpleWeightedRandomList
 *  net.minecraft.util.valueproviders.ConstantInt
 *  net.minecraft.util.valueproviders.IntProvider
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.LiquidBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
 *  net.minecraft.world.level.levelgen.feature.ConfiguredFeature
 *  net.minecraft.world.level.levelgen.feature.Feature
 *  net.minecraft.world.level.levelgen.feature.LakeFeature$Configuration
 *  net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature
 *  net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
 *  net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration
 *  net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration
 *  net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration
 *  net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration$TreeConfigurationBuilder
 *  net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize
 *  net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize
 *  net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer
 *  net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer
 *  net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer
 *  net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
 *  net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider
 *  net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator
 *  net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator
 *  net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer
 *  net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer
 *  net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.RegistryObject
 */
package fr.tathan.falloutcraft.common.worldgen.features;

import com.google.common.collect.ImmutableList;
import fr.tathan.falloutcraft.common.features.AshTopLayer;
import fr.tathan.falloutcraft.common.registries.BlocksRegistry;
import fr.tathan.falloutcraft.common.registries.FeatureRegistry;
import fr.tathan.falloutcraft.common.worldgen.features.FalloutPlacedFeatures;
import java.util.List;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.Vec3i;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class FalloutConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create((ResourceKey)Registry.CONFIGURED_FEATURE_REGISTRY, (String)"falloutcraft");
    public static final RegistryObject<ConfiguredFeature<?, ?>> IRRADIATED_OAK = CONFIGURED_FEATURES.register("irradiated_oak", () -> new ConfiguredFeature(Feature.TREE, (FeatureConfiguration)new TreeConfiguration.TreeConfigurationBuilder((BlockStateProvider)BlockStateProvider.simple((Block)Blocks.OAK_WOOD), (TrunkPlacer)new FancyTrunkPlacer(4, 7, 3), (BlockStateProvider)BlockStateProvider.simple((Block)Blocks.AIR), (FoliagePlacer)new BlobFoliagePlacer((IntProvider)ConstantInt.of((int)2), (IntProvider)ConstantInt.of((int)0), 4), (FeatureSize)new TwoLayersFeatureSize(1, 0, 2)).build()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> IRRADIATED_OAK_SPAWN = CONFIGURED_FEATURES.register("irradiated_oak_spawn", () -> new ConfiguredFeature(Feature.RANDOM_SELECTOR, (FeatureConfiguration)new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature((Holder)FalloutPlacedFeatures.IRRADIATED_OAK_CHECKED.getHolder().get(), 0.5f)), (Holder)FalloutPlacedFeatures.IRRADIATED_OAK_CHECKED.getHolder().get())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> POISONED_JUNGLE_TREE = CONFIGURED_FEATURES.register("poisoned_jungle_tree", () -> new ConfiguredFeature(Feature.TREE, (FeatureConfiguration)new TreeConfiguration.TreeConfigurationBuilder((BlockStateProvider)BlockStateProvider.simple((Block)Blocks.JUNGLE_LOG), (TrunkPlacer)new MegaJungleTrunkPlacer(10, 2, 19), (BlockStateProvider)BlockStateProvider.simple((Block)Blocks.AIR), (FoliagePlacer)new MegaJungleFoliagePlacer((IntProvider)ConstantInt.of((int)0), (IntProvider)ConstantInt.of((int)0), 0), (FeatureSize)new TwoLayersFeatureSize(1, 1, 2)).decorators((List)ImmutableList.of((Object)TrunkVineDecorator.INSTANCE, (Object)new LeaveVineDecorator(0.25f))).build()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> POISONED_JUNGLE_TREE_SPAWN_KEY = CONFIGURED_FEATURES.register("poisoned_jungle_tree_spawn", () -> new ConfiguredFeature(Feature.RANDOM_SELECTOR, (FeatureConfiguration)new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature((Holder)FalloutPlacedFeatures.POISONED_JUNGLE_TREE_CHECKED.getHolder().get(), 0.5f)), (Holder)FalloutPlacedFeatures.POISONED_JUNGLE_TREE_CHECKED.getHolder().get())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> BURNT_OAK = CONFIGURED_FEATURES.register("burnt_oak", () -> new ConfiguredFeature(Feature.TREE, (FeatureConfiguration)new TreeConfiguration.TreeConfigurationBuilder((BlockStateProvider)BlockStateProvider.simple((Block)((Block)BlocksRegistry.BURNT_OAK_LOG.get())), (TrunkPlacer)new FancyTrunkPlacer(4, 7, 3), (BlockStateProvider)BlockStateProvider.simple((Block)Blocks.AIR), (FoliagePlacer)new BlobFoliagePlacer((IntProvider)ConstantInt.of((int)2), (IntProvider)ConstantInt.of((int)0), 4), (FeatureSize)new TwoLayersFeatureSize(1, 0, 2)).build()));
    public static final RegistryObject<ConfiguredFeature<?, ?>> BURNT_OAK_SPAWN = CONFIGURED_FEATURES.register("burnt_oak_spawn", () -> new ConfiguredFeature(Feature.RANDOM_SELECTOR, (FeatureConfiguration)new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature((Holder)FalloutPlacedFeatures.BURNT_OAK_CHECKED.getHolder().get(), 0.5f)), (Holder)FalloutPlacedFeatures.BURNT_OAK_CHECKED.getHolder().get())));
    public static final RegistryObject<ConfiguredFeature<?, ?>> RADIOACTIVA = CONFIGURED_FEATURES.register("radioactiva", () -> new ConfiguredFeature(Feature.FLOWER, (FeatureConfiguration)new RandomPatchConfiguration(32, 6, 2, PlacementUtils.onlyWhenEmpty((Feature)Feature.SIMPLE_BLOCK, (FeatureConfiguration)new SimpleBlockConfiguration((BlockStateProvider)BlockStateProvider.simple((Block)((Block)BlocksRegistry.RADIOACTIVA.get())))))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> POISONED_GRASS_PATCH = CONFIGURED_FEATURES.register("poisoned_grass_patch", () -> new ConfiguredFeature(Feature.RANDOM_PATCH, (FeatureConfiguration)new RandomPatchConfiguration(16, 7, 3, PlacementUtils.filtered((Feature)Feature.SIMPLE_BLOCK, (FeatureConfiguration)new SimpleBlockConfiguration((BlockStateProvider)new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(((Block)BlocksRegistry.POISONED_GRASS.get()).defaultBlockState(), 5))), (BlockPredicate)BlockPredicate.allOf((BlockPredicate)BlockPredicate.ONLY_IN_AIR_PREDICATE, (BlockPredicate)BlockPredicate.not((BlockPredicate)BlockPredicate.matchesBlocks((Vec3i)Direction.DOWN.getNormal(), (Block[])new Block[]{Blocks.PODZOL})))))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> RADIATED_WATER_LAKE = CONFIGURED_FEATURES.register("radiared_water_lake", () -> new ConfiguredFeature(Feature.LAKE, (FeatureConfiguration)new LakeFeature.Configuration((BlockStateProvider)BlockStateProvider.simple((BlockState)((LiquidBlock)BlocksRegistry.RADIATED_WATER_BLOCK.get()).defaultBlockState()), (BlockStateProvider)BlockStateProvider.simple((BlockState)Blocks.COARSE_DIRT.defaultBlockState()))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> RADIATED_WATER_LAKE_ASH = CONFIGURED_FEATURES.register("radiared_water_lake_ash", () -> new ConfiguredFeature(Feature.LAKE, (FeatureConfiguration)new LakeFeature.Configuration((BlockStateProvider)BlockStateProvider.simple((BlockState)((LiquidBlock)BlocksRegistry.RADIATED_WATER_BLOCK.get()).defaultBlockState()), (BlockStateProvider)BlockStateProvider.simple((BlockState)((Block)BlocksRegistry.ASH_BLOCK.get()).defaultBlockState()))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> QUICKSAND_LAKE = CONFIGURED_FEATURES.register("quicksand_lake", () -> new ConfiguredFeature(Feature.LAKE, (FeatureConfiguration)new LakeFeature.Configuration((BlockStateProvider)BlockStateProvider.simple((BlockState)((Block)BlocksRegistry.QUICKSAND.get()).defaultBlockState()), (BlockStateProvider)BlockStateProvider.simple((BlockState)Blocks.COARSE_DIRT.defaultBlockState()))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> QUICKDIRT_LAKE = CONFIGURED_FEATURES.register("quickdirt_lake", () -> new ConfiguredFeature(Feature.LAKE, (FeatureConfiguration)new LakeFeature.Configuration((BlockStateProvider)BlockStateProvider.simple((BlockState)((Block)BlocksRegistry.QUICKDIRT.get()).defaultBlockState()), (BlockStateProvider)BlockStateProvider.simple((BlockState)Blocks.COARSE_DIRT.defaultBlockState()))));
    public static final RegistryObject<ConfiguredFeature<?, ?>> ASH_TOP_LAYER = CONFIGURED_FEATURES.register("ash_top_layer", () -> new ConfiguredFeature((Feature)((AshTopLayer)((Object)((Object)FeatureRegistry.ASH_TOP_LAYER.get()))), (FeatureConfiguration)FeatureConfiguration.NONE));
}

