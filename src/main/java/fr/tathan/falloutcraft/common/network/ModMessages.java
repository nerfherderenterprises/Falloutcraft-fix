/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraftforge.network.NetworkDirection
 *  net.minecraftforge.network.NetworkRegistry$ChannelBuilder
 *  net.minecraftforge.network.PacketDistributor
 *  net.minecraftforge.network.simple.SimpleChannel
 */
package fr.tathan.falloutcraft.common.network;

import fr.tathan.falloutcraft.common.network.packet.FluidSyncS2CPacket;
import fr.tathan.falloutcraft.common.network.packet.ItemStackSyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;
    private static int packetId;

    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net;
        INSTANCE = net = NetworkRegistry.ChannelBuilder.named((ResourceLocation)new ResourceLocation("falloutcraft", "messages")).networkProtocolVersion(() -> "1.0").clientAcceptedVersions(s -> true).serverAcceptedVersions(s -> true).simpleChannel();
        net.messageBuilder(FluidSyncS2CPacket.class, ModMessages.id(), NetworkDirection.PLAY_TO_CLIENT).decoder(FluidSyncS2CPacket::new).encoder(FluidSyncS2CPacket::toBytes).consumerMainThread(FluidSyncS2CPacket::handle).add();
        net.messageBuilder(ItemStackSyncS2CPacket.class, ModMessages.id(), NetworkDirection.PLAY_TO_CLIENT).decoder(ItemStackSyncS2CPacket::new).encoder(ItemStackSyncS2CPacket::toBytes).consumerMainThread(ItemStackSyncS2CPacket::handle).add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToClients(MSG message) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), message);
    }

    static {
        packetId = 0;
    }
}

