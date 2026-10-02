/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.logging.LogUtils
 *  net.minecraft.client.gui.screens.MenuScreens
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.inventory.MenuType
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.alchemy.Potion
 *  net.minecraft.world.item.alchemy.Potions
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.FlowerPotBlock
 *  net.minecraft.world.level.levelgen.SurfaceRules$RuleSource
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.brewing.BrewingRecipeRegistry
 *  net.minecraftforge.common.brewing.IBrewingRecipe
 *  net.minecraftforge.event.server.ServerStartingEvent
 *  net.minecraftforge.eventbus.api.IEventBus
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.ModLoadingContext
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 *  net.minecraftforge.fml.config.IConfigSpec
 *  net.minecraftforge.fml.config.ModConfig$Type
 *  net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
 *  net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
 *  net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
 *  net.minecraftforge.fml.loading.FMLEnvironment
 *  org.slf4j.Logger
 *  terrablender.api.Region
 *  terrablender.api.Regions
 *  terrablender.api.SurfaceRuleManager
 *  terrablender.api.SurfaceRuleManager$RuleCategory
 */
package fr.tathan.falloutcraft;

import com.mojang.logging.LogUtils;
import fr.tathan.falloutcraft.client.ClientEventHandlers;
import fr.tathan.falloutcraft.client.gui.nuka_cola_machine.NukaColaMachineScreen;
import fr.tathan.falloutcraft.client.gui.radiation_remover.RadiationRemoverScreen;
import fr.tathan.falloutcraft.client.pack.PackLoader;
import fr.tathan.falloutcraft.common.config.CommonConfig;
import fr.tathan.falloutcraft.common.fluid.ModFluidTypes;
import fr.tathan.falloutcraft.common.loot.ModLootModifiers;
import fr.tathan.falloutcraft.common.network.ModMessages;
import fr.tathan.falloutcraft.common.registries.BiomesRegistry;
import fr.tathan.falloutcraft.common.registries.BlockEntityRegistry;
import fr.tathan.falloutcraft.common.registries.BlocksRegistry;
import fr.tathan.falloutcraft.common.registries.EffectsRegistry;
import fr.tathan.falloutcraft.common.registries.FeatureRegistry;
import fr.tathan.falloutcraft.common.registries.FluidsRegistry;
import fr.tathan.falloutcraft.common.registries.ItemsRegistry;
import fr.tathan.falloutcraft.common.registries.MenuTypes;
import fr.tathan.falloutcraft.common.registries.PaintingsRegistry;
import fr.tathan.falloutcraft.common.registries.PotionsRegistry;
import fr.tathan.falloutcraft.common.registries.RecipeTypeRegistry;
import fr.tathan.falloutcraft.common.registries.SoundsRegistry;
import fr.tathan.falloutcraft.common.registries.VillagerRegistry;
import fr.tathan.falloutcraft.common.util.BetterBrewingRecipe;
import fr.tathan.falloutcraft.common.worldgen.FalloutRegion;
import fr.tathan.falloutcraft.common.worldgen.FalloutSurfaceRuleData;
import fr.tathan.falloutcraft.common.worldgen.features.FalloutConfiguredFeatures;
import fr.tathan.falloutcraft.common.worldgen.features.FalloutPlacedFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;
import terrablender.api.Region;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

@Mod(value="falloutcraft")
public class FalloutCraft {
    public static final String MODID = "falloutcraft";
    public static final Logger LOGGER = LogUtils.getLogger();

    public FalloutCraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, (IConfigSpec)CommonConfig.CONFIG);
        BlocksRegistry.BLOCKS.register(modEventBus);
        ItemsRegistry.ITEMS.register(modEventBus);
        ItemsRegistry.POTIONS.register(modEventBus);
        FeatureRegistry.FEATURES.register(modEventBus);
        EffectsRegistry.MOB_EFFECTS.register(modEventBus);
        PaintingsRegistry.PAINTING_VARIANTS.register(modEventBus);
        FluidsRegistry.FLUIDS.register(modEventBus);
        ModFluidTypes.FLUID_TYPES.register(modEventBus);
        BiomesRegistry.BIOME_REGISTER.register(modEventBus);
        FalloutConfiguredFeatures.CONFIGURED_FEATURES.register(modEventBus);
        FalloutPlacedFeatures.PLACED_FEATURES.register(modEventBus);
        SoundsRegistry.SOUNDS.register(modEventBus);
        BlockEntityRegistry.BLOCK_ENTITIES.register(modEventBus);
        MenuTypes.MENUS.register(modEventBus);
        RecipeTypeRegistry.SERIALIZERS.register(modEventBus);
        ModLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        PotionsRegistry.POTIONS.register(modEventBus);
        VillagerRegistry.register(modEventBus);
        BiomesRegistry.registerBiomes();
        modEventBus.addListener(this::commonSetup);
        if (FMLEnvironment.dist.isClient()) {
            ClientEventHandlers.init(MinecraftForge.EVENT_BUS);
            // AddPackFindersEvent is a mod-bus event (IModBusEvent): registering
            // it on the Forge bus meant the falloutcraft.extra pack was never
            // discovered. Register it on the mod bus here instead.
            modEventBus.addListener(ClientEventHandlers::discoverResourcePacks);
            PackLoader.loadOnInitialStartup();
        }
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Regions.register((Region)new FalloutRegion(new ResourceLocation(MODID, "overworld"), 3));
            ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(BlocksRegistry.RADIOACTIVA.getId(), BlocksRegistry.POTTED_RADIOACTIVA);
            ModMessages.register();
            SurfaceRuleManager.addSurfaceRules((SurfaceRuleManager.RuleCategory)SurfaceRuleManager.RuleCategory.OVERWORLD, (String)MODID, (SurfaceRules.RuleSource)FalloutSurfaceRuleData.makeRules());
            BrewingRecipeRegistry.addRecipe((IBrewingRecipe)new BetterBrewingRecipe(Potions.AWKWARD, (Item)ItemsRegistry.NUKA_COLA_CLASSIC.get(), (Potion)PotionsRegistry.RADIATION_POTION.get()));
        });
    }

    @Mod.EventBusSubscriber(modid="falloutcraft", bus=Mod.EventBusSubscriber.Bus.MOD, value={Dist.CLIENT})
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register((MenuType)((MenuType)MenuTypes.NUKA_COLA_MACHINE_MENU.get()), NukaColaMachineScreen::new);
            MenuScreens.register((MenuType)((MenuType)MenuTypes.RADIATION_REMOVER_MENU.get()), RadiationRemoverScreen::new);
        }
    }
}

