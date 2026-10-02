/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.food.FoodProperties$Builder
 *  net.minecraft.world.item.ArmorItem
 *  net.minecraft.world.item.ArmorMaterial
 *  net.minecraft.world.item.BlockItem
 *  net.minecraft.world.item.BucketItem
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.alchemy.Potion
 *  net.minecraft.world.level.block.Block
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.common.items.GeigerCounter;
import fr.tathan.falloutcraft.common.items.NukaCola;
import fr.tathan.falloutcraft.common.items.PipBoy;
import fr.tathan.falloutcraft.common.items.Radaway;
import fr.tathan.falloutcraft.common.items.Stimpak;
import fr.tathan.falloutcraft.common.registries.ArmorMaterials;
import fr.tathan.falloutcraft.common.registries.BlocksRegistry;
import fr.tathan.falloutcraft.common.registries.FluidsRegistry;
import fr.tathan.falloutcraft.common.registries.TabsRegistry;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class ItemsRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create((IForgeRegistry)ForgeRegistries.ITEMS, (String)"falloutcraft");
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create((IForgeRegistry)ForgeRegistries.POTIONS, (String)"falloutcraft");
    public static final RegistryObject<Item> NUKA_COLA_CLASSIC = ITEMS.register("nuka_cola", () -> new NukaCola(new Item.Properties().tab(TabsRegistry.TAB).food(new FoodProperties.Builder().fast().nutrition(2).saturationMod(0.2f).build()).stacksTo(16), MobEffects.MOVEMENT_SPEED));
    public static final RegistryObject<Item> NUKA_COLA_BERRY = ITEMS.register("nuka_cola_berry", () -> new NukaCola(new Item.Properties().tab(TabsRegistry.TAB).food(new FoodProperties.Builder().fast().nutrition(2).saturationMod(0.2f).build()).stacksTo(16), MobEffects.REGENERATION));
    public static final RegistryObject<Item> NUKA_COLA_MIXTURE = ITEMS.register("nuka_cola_mixture", () -> new Item(new Item.Properties().tab(TabsRegistry.TAB)));
    public static final RegistryObject<Item> NUKA_COLA_BERRY_MIXTURE = ITEMS.register("nuka_cola_berry_mixture", () -> new Item(new Item.Properties().tab(TabsRegistry.TAB)));
    public static final RegistryObject<Item> RADIATED_WATER_BUCKET = ITEMS.register("radiated_water_bucket", () -> new BucketItem(FluidsRegistry.SOURCE_RADIATED_WATER, new Item.Properties().tab(TabsRegistry.TAB).craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<BlockItem> IRRADIATED_OAK_SAPLING_ITEM = ITEMS.register("irradiated_oak_sapling", () -> new BlockItem((Block)BlocksRegistry.IRRADIATED_OAK_SAPLING.get(), new Item.Properties().tab(TabsRegistry.TAB)));
    public static final RegistryObject<Item> STIMPAK = ITEMS.register("stimpak", () -> new Stimpak(new Item.Properties().tab(TabsRegistry.TAB)));
    public static final RegistryObject<Item> RADAWAY = ITEMS.register("radaway", () -> new Radaway(new Item.Properties().tab(TabsRegistry.TAB).stacksTo(64)));
    public static final RegistryObject<Item> GEIGER_COUNTER = ITEMS.register("geiger_counter", () -> new GeigerCounter(new Item.Properties().tab(TabsRegistry.TAB)));
    public static final RegistryObject<BlockItem> PAPERS_ON_THE_GROUND_ITEM = ITEMS.register("papers_on_the_ground", () -> new BlockItem((Block)BlocksRegistry.PAPERS_ON_THE_GROUND.get(), new Item.Properties().tab(TabsRegistry.TAB_DECORATION)));
    public static final RegistryObject<BlockItem> NUKA_COLA_MACHINE_ITEM = ITEMS.register("nuka_cola_machine", () -> new BlockItem((Block)BlocksRegistry.NUKA_COLA_MACHINE.get(), new Item.Properties().tab(TabsRegistry.TAB_DECORATION)));
    public static final RegistryObject<BlockItem> RADIOACTIVA = ITEMS.register("radioactiva", () -> new BlockItem((Block)BlocksRegistry.RADIOACTIVA.get(), new Item.Properties().tab(TabsRegistry.TAB)));
    public static final RegistryObject<BlockItem> RADIATION_REMOVER_ITEM = ITEMS.register("radiation_remover", () -> new BlockItem((Block)BlocksRegistry.RADIATION_REMOVER.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> VAULT_BUTTONS = ITEMS.register("vault_buttons", () -> new BlockItem((Block)BlocksRegistry.VAULT_BUTTONS.get(), new Item.Properties()));
    public static final RegistryObject<BlockItem> QUICKSAND = ITEMS.register("quicksand", () -> new BlockItem((Block)BlocksRegistry.QUICKSAND.get(), new Item.Properties().tab(TabsRegistry.TAB)));
    public static final RegistryObject<BlockItem> QUICKDIRT = ITEMS.register("quickdirt", () -> new BlockItem((Block)BlocksRegistry.QUICKDIRT.get(), new Item.Properties().tab(TabsRegistry.TAB)));
    public static final RegistryObject<BlockItem> POISONED_GRASS = ITEMS.register("poisoned_grass", () -> new BlockItem((Block)BlocksRegistry.POISONED_GRASS.get(), new Item.Properties().tab(TabsRegistry.TAB)));
    public static final RegistryObject<BlockItem> ASH = ITEMS.register("ash", () -> new BlockItem((Block)BlocksRegistry.ASH.get(), new Item.Properties().tab(TabsRegistry.TAB)));
    public static final RegistryObject<BlockItem> ASH_BLOCK = ITEMS.register("ash_block", () -> new BlockItem((Block)BlocksRegistry.ASH_BLOCK.get(), new Item.Properties().tab(TabsRegistry.TAB)));
    public static final RegistryObject<BlockItem> BURNT_OAK_LOG = ITEMS.register("burnt_oak_log", () -> new BlockItem((Block)BlocksRegistry.BURNT_OAK_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> PIP_BOY = ITEMS.register("pip_boy", () -> new PipBoy(new Item.Properties().stacksTo(1).tab(TabsRegistry.TAB)));
    public static final RegistryObject<Item> PIMP_BOY = ITEMS.register("pimp_boy", () -> new PipBoy(new Item.Properties().stacksTo(1).tab(TabsRegistry.TAB)));
    public static final RegistryObject<Item> HAZMAT_MASK = ITEMS.register("hazmat_mask", () -> new ArmorItem((ArmorMaterial)ArmorMaterials.HAZMAT, EquipmentSlot.HEAD, new Item.Properties().tab(TabsRegistry.TAB)));
    public static final RegistryObject<Item> HAZMAT_CHESTPLATE = ITEMS.register("hazmat_chestplate", () -> new ArmorItem((ArmorMaterial)ArmorMaterials.HAZMAT, EquipmentSlot.CHEST, new Item.Properties().tab(TabsRegistry.TAB)));
    public static final RegistryObject<Item> HAZMAT_LEGGINGS = ITEMS.register("hazmat_leggings", () -> new ArmorItem((ArmorMaterial)ArmorMaterials.HAZMAT, EquipmentSlot.LEGS, new Item.Properties().tab(TabsRegistry.TAB)));
    public static final RegistryObject<Item> HAZMAT_BOOTS = ITEMS.register("hazmat_boots", () -> new ArmorItem((ArmorMaterial)ArmorMaterials.HAZMAT, EquipmentSlot.FEET, new Item.Properties().tab(TabsRegistry.TAB)));
}

