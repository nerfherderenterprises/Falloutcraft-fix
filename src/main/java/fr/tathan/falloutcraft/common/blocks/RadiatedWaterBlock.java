/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.LiquidBlock
 *  net.minecraft.world.level.block.state.BlockBehaviour
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.FlowingFluid
 */
package fr.tathan.falloutcraft.common.blocks;

import fr.tathan.falloutcraft.common.registries.EffectsRegistry;
import fr.tathan.falloutcraft.common.registries.FluidsRegistry;
import fr.tathan.falloutcraft.common.util.Methods;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

public class RadiatedWaterBlock
extends LiquidBlock {
    public RadiatedWaterBlock(Supplier<? extends FlowingFluid> pFluid, BlockBehaviour.Properties pProperties) {
        super(pFluid, pProperties);
    }

    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (Methods.isRadiationWorld(pLevel) && pEntity instanceof LivingEntity) {
            LivingEntity pEntityLiving = (LivingEntity)pEntity;
            pEntityLiving.addEffect(new MobEffectInstance((MobEffect)EffectsRegistry.RADIATION.get(), 200, 0));
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}

