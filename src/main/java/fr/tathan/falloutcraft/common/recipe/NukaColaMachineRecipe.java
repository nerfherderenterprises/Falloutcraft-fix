/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.minecraft.core.NonNullList
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.GsonHelper
 *  net.minecraft.world.SimpleContainer
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.item.crafting.RecipeSerializer
 *  net.minecraft.world.item.crafting.RecipeType
 *  net.minecraft.world.item.crafting.ShapedRecipe
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.fluids.FluidStack
 *  org.jetbrains.annotations.Nullable
 */
package fr.tathan.falloutcraft.common.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.tathan.falloutcraft.common.util.FluidJSONUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

public class NukaColaMachineRecipe
implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    private final FluidStack fluidStack;

    public NukaColaMachineRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItems, FluidStack fluidStack) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.fluidStack = fluidStack;
    }

    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide()) {
            return false;
        }
        return ((Ingredient)this.recipeItems.get(0)).test(pContainer.getItem(1));
    }

    public FluidStack getFluid() {
        return this.fluidStack;
    }

    public NonNullList<Ingredient> getIngredients() {
        return this.recipeItems;
    }

    public ItemStack assemble(SimpleContainer pContainer) {
        return this.output;
    }

    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    public ItemStack getResultItem() {
        return this.output.copy();
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Serializer
    implements RecipeSerializer<NukaColaMachineRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation("falloutcraft", "nula_cola_machine");

        public NukaColaMachineRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson((JsonObject)GsonHelper.getAsJsonObject((JsonObject)pSerializedRecipe, (String)"output"));
            JsonArray ingredients = GsonHelper.getAsJsonArray((JsonObject)pSerializedRecipe, (String)"ingredients");
            NonNullList inputs = NonNullList.withSize((int)1, (Object)Ingredient.EMPTY);
            FluidStack fluid = FluidJSONUtil.readFluid(pSerializedRecipe.get("fluid").getAsJsonObject());
            for (int i = 0; i < inputs.size(); ++i) {
                inputs.set(i, (Object)Ingredient.fromJson((JsonElement)ingredients.get(i)));
            }
            return new NukaColaMachineRecipe(pRecipeId, output, (NonNullList<Ingredient>)inputs, fluid);
        }

        @Nullable
        public NukaColaMachineRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList inputs = NonNullList.withSize((int)buf.readInt(), (Object)Ingredient.EMPTY);
            FluidStack fluid = buf.readFluidStack();
            for (int i = 0; i < inputs.size(); ++i) {
                inputs.set(i, (Object)Ingredient.fromNetwork((FriendlyByteBuf)buf));
            }
            ItemStack output = buf.readItem();
            return new NukaColaMachineRecipe(id, output, (NonNullList<Ingredient>)inputs, fluid);
        }

        public void toNetwork(FriendlyByteBuf buf, NukaColaMachineRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            buf.writeFluidStack(recipe.fluidStack);
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
        }
    }

    public static class Type
    implements RecipeType<NukaColaMachineRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "nula_cola_machine";

        private Type() {
        }
    }
}

