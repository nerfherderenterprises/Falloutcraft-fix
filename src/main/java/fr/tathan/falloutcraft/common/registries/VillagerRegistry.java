/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.world.entity.ai.village.poi.PoiType
 *  net.minecraft.world.entity.npc.VillagerProfession
 *  net.minecraft.world.level.block.Block
 *  net.minecraftforge.eventbus.api.IEventBus
 *  net.minecraftforge.fml.util.ObfuscationReflectionHelper
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package fr.tathan.falloutcraft.common.registries;

import com.google.common.collect.ImmutableSet;
import fr.tathan.falloutcraft.common.registries.BlocksRegistry;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Set;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class VillagerRegistry {
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create((IForgeRegistry)ForgeRegistries.POI_TYPES, (String)"falloutcraft");
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.create((IForgeRegistry)ForgeRegistries.VILLAGER_PROFESSIONS, (String)"falloutcraft");
    public static final RegistryObject<PoiType> NUKA_COLA_MACHINE_POI = POI_TYPES.register("nuka_cola_machine_poi", () -> new PoiType((Set)ImmutableSet.copyOf((Collection)((Block)BlocksRegistry.NUKA_COLA_MACHINE.get()).getStateDefinition().getPossibleStates()), 1, 1));
    public static final RegistryObject<VillagerProfession> SURVIVALIST = VILLAGER_PROFESSIONS.register("survivalist", () -> new VillagerProfession("survivalist", x -> x.get() == NUKA_COLA_MACHINE_POI.get(), x -> x.get() == NUKA_COLA_MACHINE_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_ARMORER));

    public static void registerPOIs() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class, (String)"registerBlockStates", (Class[])new Class[]{PoiType.class}).invoke(null, NUKA_COLA_MACHINE_POI.get());
        }
        catch (IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        }
    }

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}

