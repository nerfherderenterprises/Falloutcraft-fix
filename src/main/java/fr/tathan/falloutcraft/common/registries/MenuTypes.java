/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.inventory.AbstractContainerMenu
 *  net.minecraft.world.inventory.MenuType
 *  net.minecraftforge.common.extensions.IForgeMenuType
 *  net.minecraftforge.eventbus.api.IEventBus
 *  net.minecraftforge.network.IContainerFactory
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package fr.tathan.falloutcraft.common.registries;

import fr.tathan.falloutcraft.client.gui.nuka_cola_machine.NukaColaMachineMenu;
import fr.tathan.falloutcraft.client.gui.radiation_remover.RadiationRemoverMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

public class MenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create((IForgeRegistry)ForgeRegistries.MENU_TYPES, (String)"falloutcraft");
    public static final RegistryObject<MenuType<NukaColaMachineMenu>> NUKA_COLA_MACHINE_MENU = MenuTypes.registerMenuType(NukaColaMachineMenu::new, "nuka_cola_machine_menu");
    public static final RegistryObject<MenuType<RadiationRemoverMenu>> RADIATION_REMOVER_MENU = MenuTypes.registerMenuType(RadiationRemoverMenu::new, "radiation_remover_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create((IContainerFactory)factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}

