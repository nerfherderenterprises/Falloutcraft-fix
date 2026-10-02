/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.FlowerBlock
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 */
package fr.tathan.falloutcraft.common.blocks;

import fr.tathan.falloutcraft.common.registries.EffectsRegistry;
import fr.tathan.falloutcraft.common.registries.TagsRegistry;
import fr.tathan.falloutcraft.common.util.Methods;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class PoisonedVegetation
extends FlowerBlock {
    int effectDuration;
    MobEffect effect;

    public PoisonedVegetation(MobEffect effect, int effectDuration, BlockBehaviour.Properties pProperties) {
        super(effect, effectDuration, pProperties);
        this.effectDuration = effectDuration;
        this.effect = effect;
    }

    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return super.mayPlaceOn(pState, pLevel, pPos) || pState.is(Blocks.DIRT) || pState.is(Blocks.GRASS_BLOCK) || pState.is(Blocks.COARSE_DIRT);
    }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        LivingEntity livingentity;
        if (!pLevel.isClientSide && Methods.isRadiationWorld(pLevel) && pLevel.getDifficulty() != Difficulty.PEACEFUL && pEntity instanceof LivingEntity && !(livingentity = (LivingEntity)pEntity).getType().is(TagsRegistry.RADIATION_IMMUNISED)) {
            livingentity.addEffect(new MobEffectInstance((MobEffect)EffectsRegistry.RADIATION.get(), this.effectDuration));
        }
    }
}

