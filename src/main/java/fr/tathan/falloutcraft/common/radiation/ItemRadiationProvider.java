/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Direction
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraftforge.common.capabilities.Capability
 *  net.minecraftforge.common.capabilities.CapabilityManager
 *  net.minecraftforge.common.capabilities.CapabilityToken
 *  net.minecraftforge.common.capabilities.ICapabilityProvider
 *  net.minecraftforge.common.util.INBTSerializable
 *  net.minecraftforge.common.util.LazyOptional
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package fr.tathan.falloutcraft.common.radiation;

import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemRadiationProvider
implements ICapabilityProvider,
INBTSerializable<CompoundTag> {
    public static final Capability<ItemRadiation> ITEM_RADIATION = CapabilityManager.get((CapabilityToken)new CapabilityToken<ItemRadiation>(){});
    private ItemRadiation itemRadiation = null;
    private final LazyOptional<ItemRadiation> optional = LazyOptional.of(this::createRadiation);

    private ItemRadiation createRadiation() {
        if (this.itemRadiation == null) {
            this.itemRadiation = new ItemRadiation();
        }
        return this.itemRadiation;
    }

    @NotNull
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ITEM_RADIATION) {
            return this.optional.cast();
        }
        return LazyOptional.empty();
    }

    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        this.createRadiation().saveNBTData(nbt);
        return nbt;
    }

    public void deserializeNBT(CompoundTag nbt) {
        this.createRadiation().loadNBTData(nbt);
    }
}

