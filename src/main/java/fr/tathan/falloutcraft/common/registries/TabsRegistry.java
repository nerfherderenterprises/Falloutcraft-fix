/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.CreativeModeTab
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 */
package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.common.registries.ItemsRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class TabsRegistry {
    public static final CreativeModeTab TAB = new CreativeModeTab("falloutcraft_tab"){

        public ItemStack makeIcon() {
            return new ItemStack((ItemLike)ItemsRegistry.NUKA_COLA_CLASSIC.get());
        }
    };
    public static final CreativeModeTab TAB_DECORATION = new CreativeModeTab("decoration_tab"){

        public ItemStack makeIcon() {
            return new ItemStack((ItemLike)ItemsRegistry.PAPERS_ON_THE_GROUND_ITEM.get());
        }
    };
}

