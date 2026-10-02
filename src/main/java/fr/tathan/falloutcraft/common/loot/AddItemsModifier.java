/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Suppliers
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  com.mojang.serialization.codecs.RecordCodecBuilder$Instance
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.storage.loot.LootContext
 *  net.minecraft.world.level.storage.loot.predicates.LootItemCondition
 *  net.minecraftforge.common.loot.IGlobalLootModifier
 *  net.minecraftforge.common.loot.LootModifier
 *  net.minecraftforge.registries.ForgeRegistries
 *  org.jetbrains.annotations.NotNull
 */
package fr.tathan.falloutcraft.common.loot;

import com.google.common.base.Suppliers;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Random;
import java.util.function.Supplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class AddItemsModifier
extends LootModifier {
    public static final Supplier<Codec<AddItemsModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> AddItemsModifier.codecStart(inst).and(ForgeRegistries.ITEMS.getCodec().fieldOf("item").forGetter(m -> m.item)).and(ForgeRegistries.ITEMS.getCodec().fieldOf("item2").forGetter(m -> m.item2)).and(Codec.INT.fieldOf("multiplier").forGetter(m -> m.multiplier)).apply(inst, AddItemsModifier::new)));
    private final Item item;
    private final Item item2;
    private final int multiplier;

    protected AddItemsModifier(LootItemCondition[] conditionsIn, Item item, Item item2, int multiplier) {
        super(conditionsIn);
        this.item = item;
        this.item2 = item2;
        this.multiplier = multiplier;
    }

    @NotNull
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        Random random = new Random();
        int multiplier1 = random.nextInt(1, this.multiplier);
        for (int i = 0; i < multiplier1; ++i) {
            generatedLoot.add(new ItemStack((ItemLike)this.item));
        }
        int multiplier2 = random.nextInt(1, this.multiplier);
        for (int i = 0; i < multiplier2; ++i) {
            generatedLoot.add(new ItemStack((ItemLike)this.item2));
        }
        return generatedLoot;
    }

    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}

