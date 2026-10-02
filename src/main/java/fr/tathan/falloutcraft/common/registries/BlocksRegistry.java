/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.FlowerPotBlock
 *  net.minecraft.world.level.block.LiquidBlock
 *  net.minecraft.world.level.block.RotatedPillarBlock
 *  net.minecraft.world.level.block.SaplingBlock
 *  net.minecraft.world.level.block.SnowLayerBlock
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.grower.AbstractTreeGrower
 *  net.minecraft.world.level.block.state.BlockBehaviour
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.material.FlowingFluid
 *  net.minecraft.world.level.material.Material
 *  net.minecraft.world.level.material.MaterialColor
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.common.blocks.NukaColaMachine;
import fr.tathan.falloutcraft.common.blocks.PapersOnTheGround;
import fr.tathan.falloutcraft.common.blocks.PoisonedVegetation;
import fr.tathan.falloutcraft.common.blocks.QuickBlock;
import fr.tathan.falloutcraft.common.blocks.RadiatedWaterBlock;
import fr.tathan.falloutcraft.common.blocks.RadiationRemover;
import fr.tathan.falloutcraft.common.blocks.RadioactivaFlower;
import fr.tathan.falloutcraft.common.blocks.VaultButtons;
import fr.tathan.falloutcraft.common.registries.FluidsRegistry;
import fr.tathan.falloutcraft.common.worldgen.features.tree.BurntOakTreeGrower;
import fr.tathan.falloutcraft.common.worldgen.features.tree.IrradiatedOakGrower;
import fr.tathan.falloutcraft.common.worldgen.features.tree.PoisonedJungleTreeGrower;
import java.util.function.Supplier;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class BlocksRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create((IForgeRegistry)ForgeRegistries.BLOCKS, (String)"falloutcraft");
    public static final RegistryObject<LiquidBlock> RADIATED_WATER_BLOCK = BLOCKS.register("radiated_water_block", () -> new RadiatedWaterBlock((Supplier<? extends FlowingFluid>)FluidsRegistry.SOURCE_RADIATED_WATER, BlockBehaviour.Properties.copy((BlockBehaviour)Blocks.WATER).lightLevel(p_50872_ -> 12)));
    public static final RegistryObject<Block> PAPERS_ON_THE_GROUND = BLOCKS.register("papers_on_the_ground", () -> new PapersOnTheGround(BlockBehaviour.Properties.copy((BlockBehaviour)Blocks.CYAN_CARPET).instabreak().noOcclusion().noCollission()));
    public static final RegistryObject<Block> IRRADIATED_OAK_SAPLING = BLOCKS.register("irradiated_oak_sapling", () -> new SaplingBlock((AbstractTreeGrower)new IrradiatedOakGrower(), BlockBehaviour.Properties.copy((BlockBehaviour)Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> POISONED_JUNGLE_SAPLING = BLOCKS.register("poisoned_jungle_sapling", () -> new SaplingBlock((AbstractTreeGrower)new PoisonedJungleTreeGrower(), BlockBehaviour.Properties.copy((BlockBehaviour)Blocks.JUNGLE_SAPLING)));
    public static final RegistryObject<Block> NUKA_COLA_MACHINE = BLOCKS.register("nuka_cola_machine", () -> new NukaColaMachine(BlockBehaviour.Properties.copy((BlockBehaviour)Blocks.IRON_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> RADIATION_REMOVER = BLOCKS.register("radiation_remover", () -> new RadiationRemover(BlockBehaviour.Properties.copy((BlockBehaviour)Blocks.IRON_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> RADIOACTIVA = BLOCKS.register("radioactiva", () -> new RadioactivaFlower(MobEffects.GLOWING, BlockBehaviour.Properties.copy((BlockBehaviour)Blocks.DANDELION)));
    public static final RegistryObject<Block> POTTED_RADIOACTIVA = BLOCKS.register("potted_radioactiva", () -> new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT, RADIOACTIVA, BlockBehaviour.Properties.copy((BlockBehaviour)Blocks.DANDELION)));
    public static final RegistryObject<Block> QUICKSAND = BLOCKS.register("quicksand", () -> new QuickBlock(BlockBehaviour.Properties.of((Material)Material.SAND).noCollission().requiresCorrectToolForDrops().strength(0.5f)));
    public static final RegistryObject<Block> QUICKDIRT = BLOCKS.register("quickdirt", () -> new QuickBlock(BlockBehaviour.Properties.of((Material)Material.SAND).noCollission().requiresCorrectToolForDrops().strength(0.5f)));
    public static final RegistryObject<Block> POISONED_GRASS = BLOCKS.register("poisoned_grass", () -> new PoisonedVegetation(MobEffects.GLOWING, 40, BlockBehaviour.Properties.of((Material)Material.GRASS, (MaterialColor)MaterialColor.GRASS).noCollission()));
    public static final RegistryObject<Block> ASH = BLOCKS.register("ash", () -> new SnowLayerBlock(BlockBehaviour.Properties.of((Material)Material.TOP_SNOW).randomTicks().strength(0.1f).requiresCorrectToolForDrops().sound(SoundType.SNOW).isViewBlocking((p_187417_, p_187418_, p_187419_) -> (Integer)p_187417_.getValue((Property)SnowLayerBlock.LAYERS) >= 8).lightLevel(p_50872_ -> 3)));
    public static final RegistryObject<Block> ASH_BLOCK = BLOCKS.register("ash_block", () -> new Block(BlockBehaviour.Properties.of((Material)Material.SNOW).requiresCorrectToolForDrops().strength(0.2f).sound(SoundType.SNOW).lightLevel(p_50872_ -> 3)));
    public static final RegistryObject<Block> BURNT_OAK_LOG = BLOCKS.register("burnt_oak_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy((BlockBehaviour)Blocks.OAK_WOOD)));
    public static final RegistryObject<Block> BURNT_OAK_SAPLING = BLOCKS.register("burnt_oak_sapling", () -> new SaplingBlock((AbstractTreeGrower)new BurntOakTreeGrower(), BlockBehaviour.Properties.copy((BlockBehaviour)Blocks.OAK_SAPLING)));
    public static final RegistryObject<Block> VAULT_BUTTONS = BLOCKS.register("vault_buttons", () -> new VaultButtons(true, BlockBehaviour.Properties.copy((BlockBehaviour)Blocks.STONE_BUTTON).instabreak()));
}

