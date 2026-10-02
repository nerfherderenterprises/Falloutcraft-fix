/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.data.worldgen.SurfaceRuleData
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.levelgen.SurfaceRules
 *  net.minecraft.world.level.levelgen.SurfaceRules$ConditionSource
 *  net.minecraft.world.level.levelgen.SurfaceRules$RuleSource
 */
package fr.tathan.falloutcraft.common.worldgen;

import fr.tathan.falloutcraft.common.registries.BlocksRegistry;
import fr.tathan.falloutcraft.common.worldgen.FalloutBiomes;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class FalloutSurfaceRuleData {
    private static final SurfaceRules.RuleSource DIRT = FalloutSurfaceRuleData.makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = FalloutSurfaceRuleData.makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource STONE = FalloutSurfaceRuleData.makeStateRule(Blocks.STONE);
    private static final SurfaceRules.RuleSource COARSE_DIRT = FalloutSurfaceRuleData.makeStateRule(Blocks.COARSE_DIRT);
    private static final SurfaceRules.RuleSource ASH_BLOCK = FalloutSurfaceRuleData.makeStateRule((Block)BlocksRegistry.ASH_BLOCK.get());

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck((int)-1, (int)0);
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence((SurfaceRules.RuleSource[])new SurfaceRules.RuleSource[]{SurfaceRules.ifTrue((SurfaceRules.ConditionSource)isAtOrAboveWaterLevel, (SurfaceRules.RuleSource)COARSE_DIRT), SurfaceRuleData.overworld()});
        SurfaceRules.RuleSource ashSurface = SurfaceRules.sequence((SurfaceRules.RuleSource[])new SurfaceRules.RuleSource[]{SurfaceRules.ifTrue((SurfaceRules.ConditionSource)isAtOrAboveWaterLevel, (SurfaceRules.RuleSource)ASH_BLOCK), SurfaceRuleData.overworld()});
        return SurfaceRules.sequence((SurfaceRules.RuleSource[])new SurfaceRules.RuleSource[]{SurfaceRules.ifTrue((SurfaceRules.ConditionSource)SurfaceRules.isBiome((ResourceKey[])new ResourceKey[]{FalloutBiomes.RADIOACTIVE_PLAINS}), (SurfaceRules.RuleSource)grassSurface), SurfaceRules.ifTrue((SurfaceRules.ConditionSource)SurfaceRules.isBiome((ResourceKey[])new ResourceKey[]{FalloutBiomes.RADIOACTIVE_FOREST}), (SurfaceRules.RuleSource)grassSurface), SurfaceRules.ifTrue((SurfaceRules.ConditionSource)SurfaceRules.isBiome((ResourceKey[])new ResourceKey[]{FalloutBiomes.POISONED_JUNGLE}), (SurfaceRules.RuleSource)SurfaceRuleData.overworld()), SurfaceRules.ifTrue((SurfaceRules.ConditionSource)SurfaceRules.isBiome((ResourceKey[])new ResourceKey[]{FalloutBiomes.ASH_LAND}), (SurfaceRules.RuleSource)ashSurface), SurfaceRules.ifTrue((SurfaceRules.ConditionSource)SurfaceRules.UNDER_CEILING, (SurfaceRules.RuleSource)SurfaceRuleData.overworld())});
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state((BlockState)block.defaultBlockState());
    }
}

