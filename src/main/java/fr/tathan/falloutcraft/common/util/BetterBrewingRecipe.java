/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.alchemy.Potion
 *  net.minecraft.world.item.alchemy.PotionUtils
 *  net.minecraft.world.level.ItemLike
 *  net.minecraftforge.common.brewing.IBrewingRecipe
 */
package fr.tathan.falloutcraft.common.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class BetterBrewingRecipe
implements IBrewingRecipe {
    private final Potion input;
    private final Item ingredient;
    private final Potion output;

    public BetterBrewingRecipe(Potion input, Item ingredient, Potion output) {
        this.input = input;
        this.ingredient = ingredient;
        this.output = output;
    }

    public boolean isInput(ItemStack input) {
        return PotionUtils.getPotion((ItemStack)input) == this.input;
    }

    public boolean isIngredient(ItemStack ingredient) {
        return ingredient.getItem() == this.ingredient;
    }

    public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
        if (!this.isInput(input) || !this.isIngredient(ingredient)) {
            return ItemStack.EMPTY;
        }
        ItemStack itemStack = new ItemStack((ItemLike)input.getItem());
        itemStack.setTag(new CompoundTag());
        PotionUtils.setPotion((ItemStack)itemStack, (Potion)this.output);
        return itemStack;
    }
}

