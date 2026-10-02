/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Holder
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.block.grower.AbstractTreeGrower
 *  net.minecraft.world.level.levelgen.feature.ConfiguredFeature
 *  org.jetbrains.annotations.Nullable
 */
package fr.tathan.falloutcraft.common.worldgen.features.tree;

import fr.tathan.falloutcraft.common.worldgen.features.FalloutConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class IrradiatedOakGrower
extends AbstractTreeGrower {
    @Nullable
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pLargeHive) {
        return (Holder)FalloutConfiguredFeatures.IRRADIATED_OAK.getHolder().get();
    }
}

