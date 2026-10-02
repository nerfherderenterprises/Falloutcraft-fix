/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 */
package fr.tathan.falloutcraft.common.items;

import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import fr.tathan.falloutcraft.common.radiation.ItemRadiationProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GeigerCounter
extends Item {
    public GeigerCounter(Item.Properties pProperties) {
        super(pProperties);
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        Inventory inventory = pPlayer.getInventory();
        if (!pLevel.isClientSide) {
            int maxRadiation = 0;
            double playerRadiation = 0.0;
            for (ItemStack itemStack : inventory.items) {
                ItemRadiation radiation = (ItemRadiation)itemStack.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElse(null);
                radiation.loadNBTData(itemStack.getOrCreateTagElement("radiation"));
                Double itemRadiation = radiation.getRadiation();
                playerRadiation += itemRadiation.doubleValue();
                if (itemStack.isEmpty()) continue;
                maxRadiation += 10;
            }
            MutableComponent message = Component.literal((String)("Radiation: " + playerRadiation + "/" + maxRadiation));
            if (maxRadiation / 2 > (int)playerRadiation) {
                pPlayer.displayClientMessage((Component)Component.literal((String)("Radiation: " + playerRadiation + "/" + maxRadiation)).withStyle(ChatFormatting.DARK_GREEN), true);
            } else {
                pPlayer.displayClientMessage((Component)Component.literal((String)("Radiation: " + playerRadiation + "/" + maxRadiation)).withStyle(ChatFormatting.DARK_RED), true);
            }
        }
        return InteractionResultHolder.consume(pPlayer.getItemInHand(pUsedHand));
    }
}

