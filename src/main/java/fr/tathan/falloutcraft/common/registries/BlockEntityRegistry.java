/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.entity.BlockEntityType$Builder
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.common.blocks.entity.NukaColaMachineBlockEntity;
import fr.tathan.falloutcraft.common.blocks.entity.RadiationRemoverBlockEntity;
import fr.tathan.falloutcraft.common.registries.BlocksRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create((IForgeRegistry)ForgeRegistries.BLOCK_ENTITY_TYPES, (String)"falloutcraft");
    public static final RegistryObject<BlockEntityType<NukaColaMachineBlockEntity>> NUKA_COLA_MACHINE = BLOCK_ENTITIES.register("nuka_cola_machine", () -> BlockEntityType.Builder.of(NukaColaMachineBlockEntity::new, (Block[])new Block[]{(Block)BlocksRegistry.NUKA_COLA_MACHINE.get()}).build(null));
    public static final RegistryObject<BlockEntityType<RadiationRemoverBlockEntity>> RADIATION_REMOVER = BLOCK_ENTITIES.register("radiation_remover", () -> BlockEntityType.Builder.of(RadiationRemoverBlockEntity::new, (Block[])new Block[]{(Block)BlocksRegistry.RADIATION_REMOVER.get()}).build(null));
}

