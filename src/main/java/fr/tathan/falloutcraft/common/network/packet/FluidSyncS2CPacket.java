/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.core.BlockPos
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraftforge.fluids.FluidStack
 *  net.minecraftforge.network.NetworkEvent$Context
 */
package fr.tathan.falloutcraft.common.network.packet;

import fr.tathan.falloutcraft.client.gui.nuka_cola_machine.NukaColaMachineMenu;
import fr.tathan.falloutcraft.client.gui.radiation_remover.RadiationRemoverMenu;
import fr.tathan.falloutcraft.common.blocks.entity.NukaColaMachineBlockEntity;
import fr.tathan.falloutcraft.common.blocks.entity.RadiationRemoverBlockEntity;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

public class FluidSyncS2CPacket {
    private final FluidStack fluidStack;
    private final BlockPos pos;

    public FluidSyncS2CPacket(FluidStack fluidStack, BlockPos pos) {
        this.fluidStack = fluidStack;
        this.pos = pos;
    }

    public FluidSyncS2CPacket(FriendlyByteBuf buf) {
        this.fluidStack = buf.readFluidStack();
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeFluidStack(this.fluidStack);
        buf.writeBlockPos(this.pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            BlockEntity blockEntity = Minecraft.getInstance().level.getBlockEntity(this.pos);
            if (blockEntity instanceof NukaColaMachineBlockEntity nukaMachine) {
                nukaMachine.setFluid(this.fluidStack);
                if (Minecraft.getInstance().player.containerMenu instanceof NukaColaMachineMenu menu
                        && menu.getBlockEntity().getBlockPos().equals(this.pos)) {
                    menu.setFluid(this.fluidStack);
                }
            }
            if (blockEntity instanceof RadiationRemoverBlockEntity remover) {
                remover.setRadiatedWater(this.fluidStack);
                remover.setWater(this.fluidStack);
                if (Minecraft.getInstance().player.containerMenu instanceof RadiationRemoverMenu menu
                        && menu.getBlockEntity().getBlockPos().equals(this.pos)) {
                    menu.setRadiatedWater(this.fluidStack);
                    menu.setWater(this.fluidStack);
                }
            }
        });
        return true;
    }
}

