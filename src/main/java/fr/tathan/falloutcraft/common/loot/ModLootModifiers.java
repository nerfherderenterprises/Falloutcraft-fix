/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  net.minecraft.resources.ResourceKey
 *  net.minecraftforge.common.loot.IGlobalLootModifier
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries$Keys
 *  net.minecraftforge.registries.RegistryObject
 */
package fr.tathan.falloutcraft.common.loot;

import com.mojang.serialization.Codec;
import fr.tathan.falloutcraft.common.loot.AddItemModifier;
import fr.tathan.falloutcraft.common.loot.AddItemsModifier;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create((ResourceKey)ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, (String)"falloutcraft");
    public static RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEM_MODIFIER = LOOT_MODIFIER_SERIALIZERS.register("add_item", AddItemModifier.CODEC);
    public static RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEMS_MODIFIER = LOOT_MODIFIER_SERIALIZERS.register("add_items", AddItemsModifier.CODEC);
}

