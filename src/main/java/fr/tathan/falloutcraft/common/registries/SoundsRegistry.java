/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.sounds.SoundEvent
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package fr.tathan.falloutcraft.common.registries;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class SoundsRegistry {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create((IForgeRegistry)ForgeRegistries.SOUND_EVENTS, (String)"falloutcraft");
    public static final RegistryObject<SoundEvent> APOCALYSPE_MUSIC_1 = SOUNDS.register("apocalypse_music_1", () -> new SoundEvent(new ResourceLocation("falloutcraft", "apocalypse_music_1")));

    /** Geiger counter click: three synthesized variants, randomly picked per play. */
    public static final RegistryObject<SoundEvent> GEIGER_CLICK = SOUNDS.register("geiger_click", () -> new SoundEvent(new ResourceLocation("falloutcraft", "geiger_click")));
}

