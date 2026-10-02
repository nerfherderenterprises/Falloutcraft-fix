/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.item.alchemy.Potion
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.common.registries.EffectsRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class PotionsRegistry {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create((IForgeRegistry)ForgeRegistries.POTIONS, (String)"falloutcraft");
    public static final RegistryObject<Potion> RADIATION_POTION = POTIONS.register("radiation_potion", () -> new Potion(new MobEffectInstance[]{new MobEffectInstance((MobEffect)EffectsRegistry.RADIATION.get(), 200, 0)}));
}

