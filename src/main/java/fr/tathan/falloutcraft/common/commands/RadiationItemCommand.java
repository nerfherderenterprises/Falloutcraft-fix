/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.item.ItemStack
 */
package fr.tathan.falloutcraft.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import fr.tathan.falloutcraft.common.radiation.ItemRadiationProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

public class RadiationItemCommand {
    public RadiationItemCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)Commands.literal((String)"falloutcraft").then(((LiteralArgumentBuilder)Commands.literal((String)"addRadiation").requires(c -> c.hasPermission(2))).executes(Command -> this.addRadiation((CommandSourceStack)Command.getSource()))));
        dispatcher.register((LiteralArgumentBuilder)Commands.literal((String)"falloutcraft").then(((LiteralArgumentBuilder)Commands.literal((String)"subRadiation").requires(c -> c.hasPermission(2))).executes(Command -> this.subRadiation((CommandSourceStack)Command.getSource()))));
    }

    private int addRadiation(CommandSourceStack source) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayer();
        ItemStack item = player.getItemInHand(InteractionHand.MAIN_HAND);
        ItemRadiation radiation = (ItemRadiation)item.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElse(null);
        if (radiation == null) {
            player.sendSystemMessage((Component)Component.translatable((String)"commands.falloutcraft.addRadiation.failure", (Object[])new Object[]{item.getDisplayName()}));
            return 0;
        }
        radiation.loadNBTData(item.getOrCreateTagElement("radiation"));
        if (radiation.getRadiation() == 10.0) {
            player.sendSystemMessage((Component)Component.translatable((String)"commands.falloutcraft.addRadiation.failure", (Object[])new Object[]{item.getDisplayName()}));
        } else {
            radiation.addRadiation(1.0);
            radiation.saveNBTData(item.getOrCreateTagElement("radiation"));
            player.sendSystemMessage((Component)Component.translatable((String)"commands.falloutcraft.addRadiation.success", (Object[])new Object[]{item.getDisplayName(), radiation.getRadiation()}));
        }
        return 0;
    }

    private int subRadiation(CommandSourceStack source) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayer();
        ItemStack item = player.getItemInHand(InteractionHand.MAIN_HAND);
        ItemRadiation radiation = (ItemRadiation)item.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElse(null);
        if (radiation == null) {
            player.sendSystemMessage((Component)Component.translatable((String)"commands.falloutcraft.addRadiation.failure", (Object[])new Object[]{item.getDisplayName()}));
            return 1;
        }
        radiation.loadNBTData(item.getOrCreateTagElement("radiation"));
        if (radiation.getRadiation() == 0.0) {
            player.sendSystemMessage((Component)Component.translatable((String)"commands.falloutcraft.addRadiation.failure", (Object[])new Object[]{item.getDisplayName()}));
        } else {
            radiation.subRadiation(1.0);
            radiation.saveNBTData(item.getOrCreateTagElement("radiation"));
            player.sendSystemMessage((Component)Component.translatable((String)"commands.falloutcraft.addRadiation.success", (Object[])new Object[]{item.getDisplayName(), radiation.getRadiation()}));
        }
        return 1;
    }
}

