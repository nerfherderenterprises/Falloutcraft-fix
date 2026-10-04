/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.math.Vector3f
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraftforge.eventbus.api.IEventBus
 *  net.minecraftforge.fluids.FluidType
 *  net.minecraftforge.fluids.FluidType$Properties
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries$Keys
 *  net.minecraftforge.registries.RegistryObject
 */
package fr.tathan.falloutcraft.common.fluid;

import com.mojang.math.Vector3f;
import fr.tathan.falloutcraft.common.fluid.BaseFluidType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluidTypes {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation RADIATED_WATER_OVERLAY_RL = new ResourceLocation("falloutcraft", "misc/in_radiated_water_fluid");
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create((ResourceKey)ForgeRegistries.Keys.FLUID_TYPES, (String)"falloutcraft");
    public static final RegistryObject<FluidType> RADIATED_WATER_FLUID_TYPE = ModFluidTypes.register("radiated_water_fluid", FluidType.Properties.create().lightLevel(15).density(15).viscosity(5).supportsBoating(true));

    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, RADIATED_WATER_OVERLAY_RL, 982784, new Vector3f(0.39215687f, 0.8666667f, 0.09019608f), properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}

