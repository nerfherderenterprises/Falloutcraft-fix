/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 */
package fr.tathan.falloutcraft.common.radiation;

import net.minecraft.nbt.CompoundTag;

public class ItemRadiation {
    private double radiation = 0.0;
    private final double MIN_RADIATION = 0.0;
    private final double MAX_RADIATION = 10.0;

    public double getRadiation() {
        return this.radiation;
    }

    public void addRadiation(double add) {
        this.radiation = Math.min(this.radiation + add, 10.0);
    }

    public void subRadiation(double sub) {
        this.radiation = Math.max(this.radiation - sub, 0.0);
    }

    public void copyFrom(ItemRadiation source) {
        this.radiation = source.radiation;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putDouble("radiation", this.radiation);
    }

    public void loadNBTData(CompoundTag nbt) {
        this.radiation = nbt.getDouble("radiation");
    }
}

