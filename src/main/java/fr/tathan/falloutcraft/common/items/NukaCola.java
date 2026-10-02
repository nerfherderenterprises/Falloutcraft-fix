/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.UseAnim
 *  net.minecraft.world.level.Level
 */
package fr.tathan.falloutcraft.common.items;

import fr.tathan.falloutcraft.common.registries.EffectsRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class NukaCola
extends Item {
    MobEffect effect;

    public NukaCola(Item.Properties pProperties, MobEffect effect) {
        super(pProperties);
        this.effect = effect;
    }

    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        pEntityLiving.addEffect(new MobEffectInstance((MobEffect)EffectsRegistry.RADIATION.get(), 200, 0));
        pEntityLiving.addEffect(new MobEffectInstance(this.effect, 200, 0, false, false, true));
        return super.finishUsingItem(pStack, pLevel, pEntityLiving);
    }

    public UseAnim getUseAnimation(ItemStack pStack) {
        return pStack.getItem().isEdible() ? UseAnim.DRINK : UseAnim.NONE;
    }
}

