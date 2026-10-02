/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.decoration.PaintingVariant
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package fr.tathan.falloutcraft.common.registries;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class PaintingsRegistry {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS = DeferredRegister.create((IForgeRegistry)ForgeRegistries.PAINTING_VARIANTS, (String)"falloutcraft");
    public static final RegistryObject<PaintingVariant> NUKA_COLA_POSTER = PAINTING_VARIANTS.register("nuka_cola_poster", () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> SURFACE_NEVER_POSTER = PAINTING_VARIANTS.register("surface_never_poster", () -> new PaintingVariant(32, 32));
}

