/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mezz.jei.api.IModPlugin
 *  mezz.jei.api.JeiPlugin
 *  mezz.jei.api.recipe.RecipeType
 *  mezz.jei.api.recipe.category.IRecipeCategory
 *  mezz.jei.api.registration.IRecipeCategoryRegistration
 *  mezz.jei.api.registration.IRecipeRegistration
 *  net.minecraft.client.Minecraft
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.item.crafting.RecipeManager
 *  net.minecraft.world.item.crafting.RecipeType
 */
package fr.tathan.falloutcraft.common.integration;

import fr.tathan.falloutcraft.common.integration.NukaColaMachineRecipeCategory;
import fr.tathan.falloutcraft.common.recipe.NukaColaMachineRecipe;
import java.util.List;
import java.util.Objects;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

@JeiPlugin
public class JEIPlugin
implements IModPlugin {
    public static RecipeType<NukaColaMachineRecipe> INFUSION_TYPE = new RecipeType(NukaColaMachineRecipeCategory.UID, NukaColaMachineRecipe.class);

    public ResourceLocation getPluginUid() {
        return new ResourceLocation("falloutcraft", "jei_plugin");
    }

    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new IRecipeCategory[]{new NukaColaMachineRecipeCategory(registration.getJeiHelpers().getGuiHelper())});
    }

    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List recipesInfusing = rm.getAllRecipesFor((net.minecraft.world.item.crafting.RecipeType)NukaColaMachineRecipe.Type.INSTANCE);
        registration.addRecipes(INFUSION_TYPE, recipesInfusing);
    }
}

