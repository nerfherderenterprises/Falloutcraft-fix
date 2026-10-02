/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectCategory
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.LivingEntity
 */
package fr.tathan.falloutcraft.common.effects;

import fr.tathan.falloutcraft.common.registries.EffectsRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class NukaColaEffect
extends MobEffect {
    public NukaColaEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.addEffect(new MobEffectInstance((MobEffect)EffectsRegistry.RADIATION.get(), 2, 0, false, false, true));
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2, 0, false, false, true));
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}

