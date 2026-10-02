/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.ChatFormatting
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.client.gui.screens.inventory.InventoryScreen
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.packs.PackType
 *  net.minecraft.server.packs.repository.RepositorySource
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.TooltipFlag
 *  net.minecraftforge.client.event.ScreenEvent
 *  net.minecraftforge.client.event.ScreenEvent$Init
 *  net.minecraftforge.event.AddPackFindersEvent
 *  net.minecraftforge.event.entity.player.ItemTooltipEvent
 *  net.minecraftforge.eventbus.api.IEventBus
 *  net.minecraftforge.fml.ModList
 */
package fr.tathan.falloutcraft.client;

import fr.tathan.falloutcraft.FalloutCraft;
import fr.tathan.falloutcraft.client.pack.PackLoader;
import fr.tathan.falloutcraft.common.config.CommonConfig;
import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import fr.tathan.falloutcraft.common.radiation.ItemRadiationProvider;
import fr.tathan.falloutcraft.common.registries.ItemsRegistry;
import fr.tathan.falloutcraft.common.registries.TagsRegistry;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.RepositorySource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;

public class ClientEventHandlers {
    private static void onItemTooltip(ItemTooltipEvent event) {
        ClientEventHandlers.addItemTooltip(event.getItemStack(), event.getFlags(), event.getToolTip());
    }

    private static void onScreenOpen(ScreenEvent.Init event) {
        ClientEventHandlers.disableInventoryScreen(event.getScreen(), (ScreenEvent)event);
    }

    public static void addItemTooltip(ItemStack stack, TooltipFlag context, List<Component> tooltip) {
        if (!stack.isEmpty()) {
            ItemRadiation radiation = (ItemRadiation)stack.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElse(null);
            if (radiation == null) {
                return;
            }
            radiation.loadNBTData(stack.getOrCreateTagElement("radiation"));
            tooltip.add((Component)Component.literal((String)("Radiation: " + radiation.getRadiation())).withStyle(ChatFormatting.DARK_GREEN).withStyle(ChatFormatting.ITALIC));
        }
    }

    public static void disableInventoryScreen(Screen inventory, ScreenEvent event) {
        if (((Boolean)CommonConfig.usePimpBoy.get()).booleanValue()) {
            if (inventory.getMinecraft() == null) {
                return;
            }
            LocalPlayer player = inventory.getMinecraft().player;
            if (inventory instanceof InventoryScreen && player != null) {
                ItemStack mainHand = player.getMainHandItem();
                ItemStack offHand = player.getOffhandItem();
                if (player.level.isClientSide) {
                    if (player.isCreative()) {
                        return;
                    }
                    if (((Boolean)CommonConfig.pimpBoyUtilisation.get()).booleanValue()) {
                        if (mainHand.is(TagsRegistry.IS_PIP_BOY) || offHand.is(TagsRegistry.IS_PIP_BOY)) {
                            return;
                        }
                        inventory.onClose();
                        FalloutCraft.LOGGER.debug("Screen is canceled");
                    } else if (!((Boolean)CommonConfig.pimpBoyUtilisation.get()).booleanValue() && !player.getInventory().contains(((Item)ItemsRegistry.PIMP_BOY.get()).getDefaultInstance())) {
                        inventory.onClose();
                        FalloutCraft.LOGGER.debug("Screen is canceled");
                    }
                }
            }
        }
    }

    public static void discoverResourcePacks(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            event.addRepositorySource((RepositorySource)new PackLoader(ModList.get().getModFileById("falloutcraft").getFile()));
        }
    }

    // Registers the Forge-bus client listeners. discoverResourcePacks is a
    // mod-bus listener (AddPackFindersEvent implements IModBusEvent) and gets
    // registered on the mod bus from FalloutCraft's constructor.
    public static void init(IEventBus forgeEventBus) {
        forgeEventBus.addListener(ClientEventHandlers::onItemTooltip);
        forgeEventBus.addListener(ClientEventHandlers::onScreenOpen);
    }

    public static void openInventory(Player pPlayer) {
        Minecraft.getInstance().setScreen((Screen)new InventoryScreen(pPlayer));
    }
}

