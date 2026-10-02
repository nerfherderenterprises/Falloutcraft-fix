/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.common.worldgen.FalloutBiomes;
import fr.tathan.falloutcraft.common.worldgen.FalloutOverworldBiomes;
import java.util.function.Supplier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class BiomesRegistry {
    public static final DeferredRegister<Biome> BIOME_REGISTER = DeferredRegister.create((IForgeRegistry)ForgeRegistries.BIOMES, (String)"falloutcraft");

    public static void registerBiomes() {
        BiomesRegistry.register(FalloutBiomes.RADIOACTIVE_PLAINS, FalloutOverworldBiomes::radioactivePlains);
        BiomesRegistry.register(FalloutBiomes.RADIOACTIVE_FOREST, FalloutOverworldBiomes::radioactiveForest);
        BiomesRegistry.register(FalloutBiomes.POISONED_JUNGLE, FalloutOverworldBiomes::poisonedJungle);
        BiomesRegistry.register(FalloutBiomes.ASH_LAND, FalloutOverworldBiomes::ashLand);
    }

    public static RegistryObject<Biome> register(ResourceKey<Biome> key, Supplier<Biome> biomeSupplier) {
        return BIOME_REGISTER.register(key.location().getPath(), biomeSupplier);
    }
}

