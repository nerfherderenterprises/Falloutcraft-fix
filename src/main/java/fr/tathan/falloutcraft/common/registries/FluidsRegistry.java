/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.material.FlowingFluid
 *  net.minecraft.world.level.material.Fluid
 *  net.minecraftforge.fluids.ForgeFlowingFluid$Flowing
 *  net.minecraftforge.fluids.ForgeFlowingFluid$Properties
 *  net.minecraftforge.fluids.ForgeFlowingFluid$Source
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.common.fluid.ModFluidTypes;
import fr.tathan.falloutcraft.common.registries.BlocksRegistry;
import fr.tathan.falloutcraft.common.registries.ItemsRegistry;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class FluidsRegistry {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create((IForgeRegistry)ForgeRegistries.FLUIDS, (String)"falloutcraft");
    public static final RegistryObject<FlowingFluid> SOURCE_RADIATED_WATER = FLUIDS.register("radiated_water_fluid", () -> new ForgeFlowingFluid.Source(FluidsRegistry.RADIATED_WATER_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_RADIATED_WATER = FLUIDS.register("flowing_radiated_water", () -> new ForgeFlowingFluid.Flowing(FluidsRegistry.RADIATED_WATER_FLUID_PROPERTIES));
    public static final ForgeFlowingFluid.Properties RADIATED_WATER_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(ModFluidTypes.RADIATED_WATER_FLUID_TYPE, SOURCE_RADIATED_WATER, FLOWING_RADIATED_WATER).slopeFindDistance(2).levelDecreasePerBlock(2).block(BlocksRegistry.RADIATED_WATER_BLOCK).bucket(ItemsRegistry.RADIATED_WATER_BUCKET);
}

