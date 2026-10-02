/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.core.BlockPos
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraftforge.items.ItemStackHandler
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package fr.tathan.falloutcraft.common.network.packet;

import fr.tathan.falloutcraft.common.blocks.entity.NukaColaMachineBlockEntity;
import fr.tathan.falloutcraft.common.blocks.entity.RadiationRemoverBlockEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkEvent;

public class ItemStackSyncS2CPacket {
    private final ItemStackHandler itemStackHandler;
    private final BlockPos pos;

    public ItemStackSyncS2CPacket(ItemStackHandler itemStackHandler, BlockPos pos) {
        this.itemStackHandler = itemStackHandler;
        this.pos = pos;
    }

    public ItemStackSyncS2CPacket(FriendlyByteBuf buf) {
        List collection = (List)buf.readCollection(ArrayList::new, FriendlyByteBuf::readItem);
        this.itemStackHandler = new ItemStackHandler(collection.size());
        for (int i = 0; i < collection.size(); ++i) {
            this.itemStackHandler.insertItem(i, (ItemStack)collection.get(i), false);
        }
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        for (int i = 0; i < this.itemStackHandler.getSlots(); ++i) {
            list.add(this.itemStackHandler.getStackInSlot(i));
        }
        buf.writeCollection(list, FriendlyByteBuf::writeItem);
        buf.writeBlockPos(this.pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            BlockEntity blockEntity = Minecraft.getInstance().level.getBlockEntity(this.pos);
            if (blockEntity instanceof NukaColaMachineBlockEntity nukaMachine) {
                nukaMachine.setHandler(this.itemStackHandler);
            }
            if (blockEntity instanceof RadiationRemoverBlockEntity remover) {
                remover.setHandler(this.itemStackHandler);
            }
        });
        return true;
    }
}

