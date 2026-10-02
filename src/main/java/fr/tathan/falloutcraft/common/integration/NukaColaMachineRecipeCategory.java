/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mezz.jei.api.constants.VanillaTypes
 *  mezz.jei.api.forge.ForgeTypes
 *  mezz.jei.api.gui.builder.IRecipeLayoutBuilder
 *  mezz.jei.api.gui.builder.IRecipeSlotBuilder
 *  mezz.jei.api.gui.drawable.IDrawable
 *  mezz.jei.api.helpers.IGuiHelper
 *  mezz.jei.api.ingredients.IIngredientType
 *  mezz.jei.api.recipe.IFocusGroup
 *  mezz.jei.api.recipe.RecipeIngredientRole
 *  mezz.jei.api.recipe.RecipeType
 *  mezz.jei.api.recipe.category.IRecipeCategory
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  net.minecraft.world.level.ItemLike
 */
package fr.tathan.falloutcraft.common.integration;

import fr.tathan.falloutcraft.common.integration.JEIPlugin;
import fr.tathan.falloutcraft.common.recipe.NukaColaMachineRecipe;
import fr.tathan.falloutcraft.common.registries.BlocksRegistry;
import java.util.List;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class NukaColaMachineRecipeCategory
implements IRecipeCategory<NukaColaMachineRecipe> {
    public static final ResourceLocation UID = new ResourceLocation("falloutcraft", "nuka_cola_machine");
    public static final ResourceLocation TEXTURE = new ResourceLocation("falloutcraft", "textures/gui/nuka_cola_machine_gui.png");
    private final IDrawable background;
    private final IDrawable icon;

    public NukaColaMachineRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient((IIngredientType)VanillaTypes.ITEM_STACK, (Object)new ItemStack((ItemLike)BlocksRegistry.NUKA_COLA_MACHINE.get()));
    }

    public RecipeType<NukaColaMachineRecipe> getRecipeType() {
        return JEIPlugin.INFUSION_TYPE;
    }

    public Component getTitle() {
        return Component.literal((String)"Nuka Cola Machine");
    }

    public IDrawable getBackground() {
        return this.background;
    }

    public IDrawable getIcon() {
        return this.icon;
    }

    public void setRecipe(IRecipeLayoutBuilder builder, NukaColaMachineRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 86, 15).addIngredients((Ingredient)recipe.getIngredients().get(0));
        ((IRecipeSlotBuilder)builder.addSlot(RecipeIngredientRole.INPUT, 55, 15).addIngredients((IIngredientType)ForgeTypes.FLUID_STACK, List.of(recipe.getFluid()))).setFluidRenderer(64000L, false, 16, 61);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 60).addItemStack(recipe.getResultItem());
    }
}

