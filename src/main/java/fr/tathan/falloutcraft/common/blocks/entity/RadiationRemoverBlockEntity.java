/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.Container
 *  net.minecraft.world.Containers
 *  net.minecraft.world.MenuProvider
 *  net.minecraft.world.SimpleContainer
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.ContainerData
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.material.Fluid
 *  net.minecraft.world.level.material.Fluids
 *  net.minecraftforge.common.capabilities.Capability
 *  net.minecraftforge.common.capabilities.ForgeCapabilities
 *  net.minecraftforge.common.util.LazyOptional
 *  net.minecraftforge.fluids.FluidStack
 *  net.minecraftforge.fluids.capability.IFluidHandler
 *  net.minecraftforge.fluids.capability.IFluidHandler$FluidAction
 *  net.minecraftforge.fluids.capability.templates.FluidTank
 *  net.minecraftforge.items.IItemHandler
 *  net.minecraftforge.items.IItemHandlerModifiable
 *  net.minecraftforge.items.ItemStackHandler
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package fr.tathan.falloutcraft.common.blocks.entity;

import fr.tathan.falloutcraft.client.gui.radiation_remover.RadiationRemoverMenu;
import fr.tathan.falloutcraft.common.blocks.RadiationRemover;
import fr.tathan.falloutcraft.common.blocks.entity.WrappedHandler;
import fr.tathan.falloutcraft.common.network.ModMessages;
import fr.tathan.falloutcraft.common.network.packet.FluidSyncS2CPacket;
import fr.tathan.falloutcraft.common.network.packet.ItemStackSyncS2CPacket;
import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import fr.tathan.falloutcraft.common.radiation.ItemRadiationProvider;
import fr.tathan.falloutcraft.common.registries.BlockEntityRegistry;
import fr.tathan.falloutcraft.common.registries.FluidsRegistry;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RadiationRemoverBlockEntity
extends BlockEntity
implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4){

        protected void onContentsChanged(int slot) {
            RadiationRemoverBlockEntity.this.setChanged();
            if (!RadiationRemoverBlockEntity.this.level.isClientSide()) {
                ModMessages.sendToClients(new ItemStackSyncS2CPacket(this, RadiationRemoverBlockEntity.this.worldPosition));
            }
        }

        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent();
                case 1 -> true;
                case 2 -> false;
                default -> super.isItemValid(slot, stack);
            };
        }
    };
    private final FluidTank WATER_TANK = new FluidTank(64000){

        protected void onContentsChanged() {
            RadiationRemoverBlockEntity.this.setChanged();
            if (!RadiationRemoverBlockEntity.this.level.isClientSide()) {
                ModMessages.sendToClients(new FluidSyncS2CPacket(this.fluid, RadiationRemoverBlockEntity.this.worldPosition));
            }
        }

        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() == Fluids.WATER;
        }
    };
    private final FluidTank RADIATED_WATER_TANK = new FluidTank(64000){

        protected void onContentsChanged() {
            RadiationRemoverBlockEntity.this.setChanged();
            if (!RadiationRemoverBlockEntity.this.level.isClientSide()) {
                ModMessages.sendToClients(new FluidSyncS2CPacket(this.fluid, RadiationRemoverBlockEntity.this.worldPosition));
            }
        }

        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() == Fluids.WATER;
        }
    };
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap = Map.of(Direction.DOWN, LazyOptional.of(() -> new WrappedHandler((IItemHandlerModifiable)this.itemHandler, i -> i == 2, (i, s) -> false)), Direction.NORTH, LazyOptional.of(() -> new WrappedHandler((IItemHandlerModifiable)this.itemHandler, index -> index == 1, (index, stack) -> this.itemHandler.isItemValid(1, stack))), Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler((IItemHandlerModifiable)this.itemHandler, i -> i == 2, (i, s) -> false)), Direction.EAST, LazyOptional.of(() -> new WrappedHandler((IItemHandlerModifiable)this.itemHandler, i -> i == 1, (index, stack) -> this.itemHandler.isItemValid(1, stack))), Direction.WEST, LazyOptional.of(() -> new WrappedHandler((IItemHandlerModifiable)this.itemHandler, index -> index == 0 || index == 1, (index, stack) -> this.itemHandler.isItemValid(0, stack) || this.itemHandler.isItemValid(1, stack))));
    private LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();
    protected final ContainerData data = new ContainerData(){

        public int get(int index) {
            return switch (index) {
                case 0 -> RadiationRemoverBlockEntity.this.progress;
                case 1 -> RadiationRemoverBlockEntity.this.maxProgress;
                default -> 0;
            };
        }

        public void set(int index, int value) {
            switch (index) {
                case 0: {
                    RadiationRemoverBlockEntity.this.progress = value;
                    break;
                }
                case 1: {
                    RadiationRemoverBlockEntity.this.maxProgress = value;
                }
            }
        }

        public int getCount() {
            return 2;
        }
    };
    private int progress = 0;
    private int maxProgress = 78;

    public void setWater(FluidStack stack) {
        this.WATER_TANK.setFluid(stack);
    }

    public FluidStack getWaterStack() {
        return this.WATER_TANK.getFluid();
    }

    public void setRadiatedWater(FluidStack stack) {
        this.RADIATED_WATER_TANK.setFluid(stack);
    }

    public FluidStack getRadiatedWaterStack() {
        return this.RADIATED_WATER_TANK.getFluid();
    }

    public ItemStack getRenderStack() {
        ItemStack stack = !this.itemHandler.getStackInSlot(2).isEmpty() ? this.itemHandler.getStackInSlot(2) : this.itemHandler.getStackInSlot(1);
        return stack;
    }

    public void setHandler(ItemStackHandler itemStackHandler) {
        for (int i = 0; i < itemStackHandler.getSlots(); ++i) {
            this.itemHandler.setStackInSlot(i, itemStackHandler.getStackInSlot(i));
        }
    }

    public RadiationRemoverBlockEntity(BlockPos pos, BlockState state) {
        super((BlockEntityType)BlockEntityRegistry.RADIATION_REMOVER.get(), pos, state);
    }

    public Component getDisplayName() {
        return Component.literal((String)"Radiation Remover");
    }

    @Nullable
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        ModMessages.sendToClients(new FluidSyncS2CPacket(this.getWaterStack(), this.worldPosition));
        ModMessages.sendToClients(new FluidSyncS2CPacket(this.getRadiatedWaterStack(), this.worldPosition));
        return new RadiationRemoverMenu(id, inventory, this, this.data);
    }

    @NotNull
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            if (side == null) {
                return this.lazyItemHandler.cast();
            }
            if (this.directionWrappedHandlerMap.containsKey(side)) {
                Direction localDir = (Direction)this.getBlockState().getValue((Property)RadiationRemover.FACING);
                if (side == Direction.UP || side == Direction.DOWN) {
                    return this.directionWrappedHandlerMap.get(side).cast();
                }
                return switch (localDir) {
                    default -> this.directionWrappedHandlerMap.get(side.getOpposite()).cast();
                    case EAST -> this.directionWrappedHandlerMap.get(side.getClockWise()).cast();
                    case SOUTH -> this.directionWrappedHandlerMap.get(side).cast();
                    case WEST -> this.directionWrappedHandlerMap.get(side.getCounterClockWise()).cast();
                };
            }
        }
        if (cap == ForgeCapabilities.FLUID_HANDLER) {
            return this.lazyFluidHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    public void onLoad() {
        super.onLoad();
        this.lazyItemHandler = LazyOptional.of(() -> this.itemHandler);
        this.lazyFluidHandler = LazyOptional.of(() -> this.WATER_TANK);
        this.lazyFluidHandler = LazyOptional.of(() -> this.RADIATED_WATER_TANK);
    }

    public void invalidateCaps() {
        super.invalidateCaps();
        this.lazyItemHandler.invalidate();
        this.lazyFluidHandler.invalidate();
    }

    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", (Tag)this.itemHandler.serializeNBT());
        nbt.putInt("radiation_remover.progress", this.progress);
        nbt = this.WATER_TANK.writeToNBT(nbt);
        nbt = this.RADIATED_WATER_TANK.writeToNBT(nbt);
        super.saveAdditional(nbt);
    }

    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        this.progress = nbt.getInt("radiation_remover.progress");
        this.WATER_TANK.readFromNBT(nbt);
        this.RADIATED_WATER_TANK.readFromNBT(nbt);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for (int i = 0; i < this.itemHandler.getSlots(); ++i) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }
        Containers.dropContents((Level)this.level, (BlockPos)this.worldPosition, (Container)inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, RadiationRemoverBlockEntity pEntity) {
        if (level.isClientSide()) {
            return;
        }
        if (RadiationRemoverBlockEntity.hasRecipe(pEntity)) {
            ++pEntity.progress;
            RadiationRemoverBlockEntity.setChanged((Level)level, (BlockPos)pos, (BlockState)state);
            if (pEntity.progress >= pEntity.maxProgress) {
                RadiationRemoverBlockEntity.craftItem(pEntity);
            }
        } else {
            pEntity.resetProgress();
            RadiationRemoverBlockEntity.setChanged((Level)level, (BlockPos)pos, (BlockState)state);
        }
        if (RadiationRemoverBlockEntity.hasWaterItemInSourceSlot(pEntity)) {
            RadiationRemoverBlockEntity.transferItemFluidToWaterTank(pEntity);
        }
    }

    private static void transferItemFluidToWaterTank(RadiationRemoverBlockEntity pEntity) {
        pEntity.itemHandler.getStackInSlot(0).getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).ifPresent(handler -> {
            int drainAmount = Math.min(pEntity.WATER_TANK.getSpace(), 1000);
            FluidStack stack = handler.drain(drainAmount, IFluidHandler.FluidAction.SIMULATE);
            if (pEntity.WATER_TANK.isFluidValid(stack)) {
                stack = handler.drain(drainAmount, IFluidHandler.FluidAction.EXECUTE);
                RadiationRemoverBlockEntity.fillTankWithWater(pEntity, stack, handler.getContainer());
            }
        });
    }

    private static void transferRadiatedWaterToItem(RadiationRemoverBlockEntity pEntity) {
        pEntity.itemHandler.getStackInSlot(3).getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).ifPresent(handler -> {
            int drainAmount = Math.min(pEntity.RADIATED_WATER_TANK.getSpace(), 1000);
            FluidStack stack = handler.drain(drainAmount, IFluidHandler.FluidAction.SIMULATE);
            if (pEntity.WATER_TANK.isFluidValid(stack)) {
                stack = handler.drain(drainAmount, IFluidHandler.FluidAction.EXECUTE);
                RadiationRemoverBlockEntity.fillTankWithWater(pEntity, stack, handler.getContainer());
            }
        });
    }

    private static void fillTankWithWater(RadiationRemoverBlockEntity pEntity, FluidStack stack, ItemStack container) {
        pEntity.WATER_TANK.fill(stack, IFluidHandler.FluidAction.EXECUTE);
        pEntity.itemHandler.extractItem(0, 1, false);
        pEntity.itemHandler.insertItem(0, container, false);
    }

    private static void fillTankWithRadiatedWater(RadiationRemoverBlockEntity pEntity, FluidStack stack, ItemStack container) {
        pEntity.RADIATED_WATER_TANK.fill(stack, IFluidHandler.FluidAction.EXECUTE);
    }

    private static boolean hasWaterItemInSourceSlot(RadiationRemoverBlockEntity pEntity) {
        return pEntity.itemHandler.getStackInSlot(0).getCount() > 0;
    }

    private static boolean hasBucketItemInSourceSlot(RadiationRemoverBlockEntity pEntity) {
        return pEntity.itemHandler.getStackInSlot(3).getCount() > 0;
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(RadiationRemoverBlockEntity pEntity) {
        if (RadiationRemoverBlockEntity.hasRecipe(pEntity)) {
            pEntity.WATER_TANK.drain(250, IFluidHandler.FluidAction.EXECUTE);
            pEntity.RADIATED_WATER_TANK.fill(new FluidStack((Fluid)FluidsRegistry.FLOWING_RADIATED_WATER.get(), 250), IFluidHandler.FluidAction.EXECUTE);
            pEntity.itemHandler.extractItem(1, 1, false);
            ItemStack output = pEntity.itemHandler.getStackInSlot(1);
            ItemRadiation itemRadiation = (ItemRadiation)output.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElse(null);
            if (itemRadiation != null) {
                itemRadiation.subRadiation(0.5);
            }
            pEntity.itemHandler.setStackInSlot(2, new ItemStack((ItemLike)output.getItem(), pEntity.itemHandler.getStackInSlot(2).getCount() + 1));
            pEntity.resetProgress();
        }
    }

    private static boolean hasRecipe(RadiationRemoverBlockEntity entity) {
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); ++i) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }
        boolean hasItemInFirstSlot = entity.itemHandler.getStackInSlot(1).getItem() != null;
        return hasItemInFirstSlot && RadiationRemoverBlockEntity.hasCorrectFluidAmountInTank(entity, 250) && RadiationRemoverBlockEntity.canInsertAmountIntoOutputSlot(inventory) && RadiationRemoverBlockEntity.canInsertItemIntoOutputSlot(inventory, new ItemStack((ItemLike)entity.itemHandler.getStackInSlot(1).getItem(), 1));
    }

    private static boolean hasCorrectFluidAmountInTank(RadiationRemoverBlockEntity entity, int amount) {
        return entity.WATER_TANK.getFluidAmount() >= amount;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(2).getItem() == stack.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }
}

