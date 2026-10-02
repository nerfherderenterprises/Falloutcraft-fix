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

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class RadiationsEffect
extends MobEffect {
    public RadiationsEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 5, 0, false, false, false));
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 5, 0, false, false, false));
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 5, 0, false, false, false));
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}

