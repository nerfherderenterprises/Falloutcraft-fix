/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Registry
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.item.Item
 */
package fr.tathan.falloutcraft.common.registries;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class TagsRegistry {
    public static final TagKey<EntityType<?>> RADIATION_IMMUNISED;
    public static final TagKey<Item> VERY_RADIOACTIVE;
    public static final TagKey<Item> IS_PIP_BOY;

    static {
        VERY_RADIOACTIVE = TagKey.create((ResourceKey)Registry.ITEM_REGISTRY, (ResourceLocation)new ResourceLocation("falloutcraft", "very_radioactive"));
        IS_PIP_BOY = TagKey.create((ResourceKey)Registry.ITEM_REGISTRY, (ResourceLocation)new ResourceLocation("falloutcraft", "pip_boy"));
        RADIATION_IMMUNISED = TagKey.create((ResourceKey)Registry.ENTITY_TYPE_REGISTRY, (ResourceLocation)new ResourceLocation("falloutcraft", "radiation_immunised"));
    }
}

