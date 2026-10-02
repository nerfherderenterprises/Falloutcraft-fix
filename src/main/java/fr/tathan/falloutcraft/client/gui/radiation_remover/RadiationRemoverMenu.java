/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.ContainerData
 *  net.minecraft.world.inventory.ContainerLevelAccess
 *  net.minecraft.world.inventory.MenuType
 *  net.minecraft.world.inventory.SimpleContainerData
 *  net.minecraft.world.inventory.Slot
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraftforge.fluids.FluidStack
 *  net.minecraftforge.items.CapabilityItemHandler
 *  net.minecraftforge.items.SlotItemHandler
 */
package fr.tathan.falloutcraft.client.gui.radiation_remover;

import fr.tathan.falloutcraft.common.blocks.entity.RadiationRemoverBlockEntity;
import fr.tathan.falloutcraft.common.registries.BlocksRegistry;
import fr.tathan.falloutcraft.common.registries.MenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class RadiationRemoverMenu
extends AbstractContainerMenu {
    public final RadiationRemoverBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;
    private FluidStack waterStack;
    private FluidStack radiatedWaterStack;
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = 27;
    private static final int VANILLA_SLOT_COUNT = 36;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = 36;
    private static final int TE_INVENTORY_SLOT_COUNT = 4;

    public RadiationRemoverMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, inv.player.level.getBlockEntity(extraData.readBlockPos()), (ContainerData)new SimpleContainerData(2));
    }

    public RadiationRemoverMenu(int id, Inventory inv, BlockEntity entity, ContainerData data) {
        super((MenuType)MenuTypes.RADIATION_REMOVER_MENU.get(), id);
        RadiationRemoverMenu.checkContainerSize((Container)inv, (int)4);
        this.blockEntity = (RadiationRemoverBlockEntity)entity;
        this.level = inv.player.level;
        this.data = data;
        this.waterStack = this.blockEntity.getWaterStack();
        this.radiatedWaterStack = this.blockEntity.getRadiatedWaterStack();
        this.addPlayerInventory(inv);
        this.addPlayerHotbar(inv);
        this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            this.addSlot((Slot)new SlotItemHandler(handler, 0, 17, 17));
            this.addSlot((Slot)new SlotItemHandler(handler, 1, 79, 17));
            this.addSlot((Slot)new SlotItemHandler(handler, 2, 141, 17));
            this.addSlot((Slot)new SlotItemHandler(handler, 3, 79, 58));
        });
        this.addDataSlots(data);
    }

    public boolean isCrafting() {
        return this.data.get(0) > 0;
    }

    public void setWater(FluidStack water) {
        this.waterStack = water;
    }

    public void setRadiatedWater(FluidStack water) {
        this.radiatedWaterStack = water;
    }

    public RadiationRemoverBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);
        int progressArrowSize = 26;
        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = (Slot)this.slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) {
            return ItemStack.EMPTY;
        }
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();
        if (index < 36) {
            if (!this.moveItemStackTo(sourceStack, 36, 40, false)) {
                return ItemStack.EMPTY;
            }
        } else if (index < 40) {
            if (!this.moveItemStackTo(sourceStack, 0, 36, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    public boolean stillValid(Player player) {
        return RadiationRemoverMenu.stillValid((ContainerLevelAccess)ContainerLevelAccess.create((Level)this.level, (BlockPos)this.blockEntity.getBlockPos()), (Player)player, (Block)((Block)BlocksRegistry.RADIATION_REMOVER.get()));
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot((Container)playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot((Container)playerInventory, i, 8 + i * 18, 144));
        }
    }

    public FluidStack getWaterStack() {
        return this.waterStack;
    }

    public FluidStack getRadiatedWaterStack() {
        return this.radiatedWaterStack;
    }
}

