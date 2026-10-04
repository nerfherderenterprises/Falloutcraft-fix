/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.npc.VillagerProfession
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.trading.MerchantOffer
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.common.capabilities.ICapabilityProvider
 *  net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent
 *  net.minecraftforge.event.AttachCapabilitiesEvent
 *  net.minecraftforge.event.ItemStackedOnOtherEvent
 *  net.minecraftforge.event.RegisterCommandsEvent
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.event.TickEvent$PlayerTickEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingTickEvent
 *  net.minecraftforge.event.entity.player.EntityItemPickupEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$Clone
 *  net.minecraftforge.event.village.VillagerTradesEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fluids.FluidType
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.server.command.ConfigCommand
 */
package fr.tathan.falloutcraft.common.events;

import com.mojang.brigadier.CommandDispatcher;
import fr.tathan.falloutcraft.common.commands.RadiationItemCommand;
import fr.tathan.falloutcraft.common.config.CommonConfig;
import fr.tathan.falloutcraft.common.fluid.ModFluidTypes;
import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import fr.tathan.falloutcraft.common.radiation.ItemRadiationProvider;
import fr.tathan.falloutcraft.common.registries.BlocksRegistry;
import fr.tathan.falloutcraft.common.registries.EffectsRegistry;
import fr.tathan.falloutcraft.common.registries.ItemsRegistry;
import fr.tathan.falloutcraft.common.registries.SoundsRegistry;
import net.minecraft.sounds.SoundSource;
import fr.tathan.falloutcraft.common.registries.TagsRegistry;
import fr.tathan.falloutcraft.common.util.Methods;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import java.util.List;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.ItemStackedOnOtherEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid="falloutcraft")
public class Events {
    /**
     * True when the player's inventory holds a filled radiated-water
     * bucket (main inventory or offhand). Placed buckets don't count -
     * setting the bucket down moves the hazard into the block-anchored
     * patch instead.
     */
    private static boolean carriesRadiatedBucket(Player player) {
        for (ItemStack stack : player.getInventory().items) {
            if (stack.is(ItemsRegistry.RADIATED_WATER_BUCKET.get())) {
                return true;
            }
        }
        for (ItemStack stack : player.getInventory().offhand) {
            if (stack.is(ItemsRegistry.RADIATED_WATER_BUCKET.get())) {
                return true;
            }
        }
        return false;
    }

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            Level level = player.level;
            if (!level.isClientSide) {
                // Geiger counter: clicks follow total exposure. This runs
                // BEFORE the world-type gate on purpose - carried item rads
                // are global, so the counter clicks in the default world
                // type too; the ambient readings are world-gated inside
                // geigerLevel() itself.
                boolean holdingCounter = player.getMainHandItem().is(ItemsRegistry.GEIGER_COUNTER.get()) || player.getOffhandItem().is(ItemsRegistry.GEIGER_COUNTER.get());
                if (holdingCounter) {
                    float reading = Methods.geigerLevel(player);
                    if (reading > 0.0f && player.getRandom().nextFloat() < 0.01f + reading * 0.12f) {
                        level.playSound(null, player, SoundsRegistry.GEIGER_CLICK.get(), SoundSource.PLAYERS, 1.0f, 0.9f + player.getRandom().nextFloat() * 0.25f);
                    }
                }
            }
            // Irradiated water is global (Jimmy, Oct 3 ruling): it must
            // be radioactive, bright and injurious in BOTH the default
            // and the wasteland world type - hence the very small AoE:
            // the fluid you stand in, a 3x3x2 patch around a placed
            // source (bottom center, one block out, one layer up), or a
            // 3x3x3 box around a carried filled bucket. Never more than
            // a block away, so the default world stays livable. These
            // run BEFORE the world-type gate on purpose; every other
            // radiation vector still stops at the gate below.
            if (player.isInFluidType((FluidType)ModFluidTypes.RADIATED_WATER_FLUID_TYPE.get()) && !Methods.hasHazmatSuit(player)) {
                player.addEffect(new MobEffectInstance((MobEffect)EffectsRegistry.RADIATION.get(), 10));
                if (!player.getAbilities().instabuild && player.getRandom().nextFloat() < 0.002f) {
                    // Immersion clock: ~0.5 HP every 25 s = death from a long
                    // swim in ~16 minutes (regeneration is suppressed while
                    // the badge shows, so the rate is honest against a fed
                    // player too).
                    player.hurt(Methods.DAMAGE_SOURCE_RADIATED_WATER, 0.5f);
                }
            }
            if (!player.isSpectator() && !Methods.hasHazmatSuit(player)) {
                // Ouch radius: a 3x3x2 hostile patch anchored on radiated
                // water - water at the bottom center, one block out
                // horizontally, one layer up. Standing in, beside, or on top
                // of irradiated water counts (feet at the water's level or
                // one above it). Same exposure model as the biome: badge +
                // slow kill clock; hazmat blocks it. Global like the fluid
                // itself - the small box is what makes it safe to run in
                // the default world type. A placed bucket of radiated water
                // carries this zone with it.
                boolean nearWater = false;
                for (int dx = -1; dx <= 1 && !nearWater; dx++) {
                    for (int dz = -1; dz <= 1 && !nearWater; dz++) {
                        for (int dy = 0; dy <= 1; dy++) {
                            if (level.getBlockState(player.blockPosition().offset(dx, -dy, dz)).getBlock() == BlocksRegistry.RADIATED_WATER_BLOCK.get()) {
                                nearWater = true;
                                break;
                            }
                        }
                    }
                }
                // Carried bucket: the bucket sits at the carrier's center,
                // so a carried pool radiates a 3x3x3 box - one block out on
                // every axis - that travels with them. Carrier and any
                // close company (feet within one block on all three axes)
                // share the dose. Filled bucket item only: placing it moves
                // the zone back to the block-anchored 3x3x2 patch above.
                boolean carriedBucket = carriesRadiatedBucket(player);
                if (!carriedBucket) {
                    BlockPos feet = player.blockPosition();
                    for (Player other : level.players()) {
                        if (other != player
                                && Math.abs(other.blockPosition().getX() - feet.getX()) <= 1
                                && Math.abs(other.blockPosition().getY() - feet.getY()) <= 1
                                && Math.abs(other.blockPosition().getZ() - feet.getZ()) <= 1
                                && carriesRadiatedBucket(other)) {
                            carriedBucket = true;
                            break;
                        }
                    }
                }
                if (nearWater || carriedBucket) {
                    player.addEffect(new MobEffectInstance((MobEffect)EffectsRegistry.RADIATION.get(), 40));
                    if (!player.getAbilities().instabuild && player.getRandom().nextFloat() < 0.002f) {
                        player.hurt(Methods.DAMAGE_SOURCE_RADIATED_WATER, 0.5f);
                    }
                }
            }
            if (!Methods.isRadiationWorld(level)) {
                // Tier-3 world-type gate: the radiation system is not
                // implemented in the default world type. No ambient biome
                // dose, no radioactive rain, no item-radiation ladder -
                // irradiated items keep their values (global), they just
                // stop acting on you here. Irradiated water is exempt
                // (global small AoE, see above).
                return;
            }
            if (player.isInWater() && !Methods.hasHazmatSuit(player)) {
                // Tainted water: in the radiation world every pool counts
                // (TATHAN's original rule) - separate from the global
                // radiated-fluid check above; the two fluids never
                // overlap, so this never double-doses.
                player.addEffect(new MobEffectInstance((MobEffect)EffectsRegistry.RADIATION.get(), 10));
                if (!player.getAbilities().instabuild && player.getRandom().nextFloat() < 0.002f) {
                    player.hurt(Methods.DAMAGE_SOURCE_RADIATED_WATER, 0.5f);
                }
            }
            if (!player.isSpectator() && !Methods.hasHazmatSuit(player) && Methods.isWastelandBiome(level, player.blockPosition())) {
                // Ambient radiation: the wasteland itself is the hazard
                // (spec: biome-local with a hard border, hazard only - no
                // energy economy). The badge shows continuously while you
                // stand in an irradiated biome - it marks the exposure and
                // suppresses natural regeneration; the kill clock is the
                // slow roll below. No creative gate on the badge (the biome
                // is visibly irradiated for everyone); only damage and
                // healing respect creative mode.
                player.addEffect(new MobEffectInstance((MobEffect)EffectsRegistry.RADIATION.get(), 40));
                if (!player.getAbilities().instabuild && player.getRandom().nextFloat() < 0.002f) {
                    // ~0.5 HP every 25 s = ~16 minutes of standing around
                    // in the fallout. Sources stack: rain + water + ambient
                    // together triple the dose, halving the time.
                    player.hurt(Methods.DAMAGE_SOURCE_RADIATION, 0.5f);
                }
            }
            if (!player.isCreative() && ((Boolean)CommonConfig.itemRadiationDamage.get()).booleanValue() && event.player.getRandom().nextFloat() < 0.005f) {
                for (ItemStack itemStack : player.getInventory().items) {
                    ItemRadiation radiation = (ItemRadiation)itemStack.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElse(null);
                    if (radiation == null) {
                        continue;
                    }
                    radiation.loadNBTData(itemStack.getOrCreateTagElement("radiation"));
                    Double itemRadiation = radiation.getRadiation();
                    if (itemRadiation >= 2.0 && itemRadiation < 3.0) {
                        player.addEffect(new MobEffectInstance((MobEffect)EffectsRegistry.RADIATION.get(), 30));
                        continue;
                    }
                    if (itemRadiation >= 3.0 && itemRadiation < 4.0) {
                        player.addEffect(new MobEffectInstance((MobEffect)EffectsRegistry.RADIATION.get(), 40));
                        continue;
                    }
                    if (itemRadiation >= 4.0 && itemRadiation < 5.0) {
                        player.addEffect(new MobEffectInstance((MobEffect)EffectsRegistry.RADIATION.get(), 50));
                        continue;
                    }
                    if (itemRadiation >= 5.0 && itemRadiation < 6.0) {
                        player.addEffect(new MobEffectInstance((MobEffect)EffectsRegistry.RADIATION.get(), 60));
                        continue;
                    }
                    if (!(itemRadiation >= 6.0) && !itemStack.is(TagsRegistry.VERY_RADIOACTIVE)) continue;
                    player.addEffect(new MobEffectInstance((MobEffect)EffectsRegistry.RADIATION.get(), 70));
                }
            }
        }
    }

    @SubscribeEvent
    public static void livingEntityTick(LivingEvent.LivingTickEvent event) {
        LivingEntity livingEntity = event.getEntity();
        Methods.radioactiveRain(livingEntity, (ResourceKey<Level>)Level.OVERWORLD);
    }

    @SubscribeEvent
    public static void itemPickupEvent(EntityItemPickupEvent event) {
        if (!Methods.isRadiationWorld(event.getEntity().level)) {
            // Picking items up only contaminates them in a radiation world;
            // in the default world type items never gain radiation here.
            return;
        }
        ItemStack itemStack = event.getItem().getItem();
        ItemRadiation itemRadiation = (ItemRadiation)itemStack.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElse(null);
        if (itemRadiation == null) {
            return;
        }
        if (itemRadiation.getRadiation() < 7.0) {
            itemRadiation.addRadiation(0.5);
            itemRadiation.saveNBTData(itemStack.getOrCreateTagElement("radiation"));
        }
    }

    @SubscribeEvent
    public static void onRadiationHeal(LivingHealEvent event) {
        if (event.getEntity() instanceof Player player && Methods.isRadiationWorld(player.level) && player.hasEffect((MobEffect)EffectsRegistry.RADIATION.get())) {
            // Irradiated players don't regenerate. Fed players heal 0.25
            // HP/s (2 HP/s with saturation), which would out-heal any
            // survivable-looking exposure rate into functional immortality
            // - zeroing heals while the badge shows is what lets the slow
            // ~15-minute kill clocks actually land. Recovery = leave the
            // biome, suit up, or milk the badge off. In the default world
            // type the badge never appears, so healing is untouched there
            // (spec: radiation system absent entirely).
            event.setAmount(0.0f);
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath() && !event.getEntity().getInventory().contains(((Item)ItemsRegistry.PIMP_BOY.get()).getDefaultInstance())) {
            event.getEntity().addItem(((Item)ItemsRegistry.PIMP_BOY.get()).getDefaultInstance());
        }
    }

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.CLERIC) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack nuka_cola_classic = new ItemStack((ItemLike)ItemsRegistry.NUKA_COLA_CLASSIC.get(), 1);
            ItemStack nuka_cola_berry = new ItemStack((ItemLike)ItemsRegistry.NUKA_COLA_BERRY.get(), 1);
            ItemStack pimp_boy = new ItemStack((ItemLike)ItemsRegistry.PIMP_BOY.get(), 1);
            trades.get(2).add((trader, rand) -> new MerchantOffer(new ItemStack((ItemLike)Items.EMERALD, 2), nuka_cola_classic, 1, 12, 0.09f));
            trades.get(2).add((trader, rand) -> new MerchantOffer(new ItemStack((ItemLike)Items.EMERALD, 2), nuka_cola_berry, 1, 12, 0.09f));
            trades.get(1).add((trader, rand) -> new MerchantOffer(new ItemStack((ItemLike)Items.EMERALD, 1), pimp_boy, 200, 1, 0.05f));
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesItemStack(AttachCapabilitiesEvent<ItemStack> event) {
        event.addCapability(new ResourceLocation("falloutcraft", "item_radiation"), (ICapabilityProvider)new ItemRadiationProvider());
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(ItemRadiation.class);
    }

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new RadiationItemCommand((CommandDispatcher<CommandSourceStack>)event.getDispatcher());
        ConfigCommand.register((CommandDispatcher)event.getDispatcher());
    }

    @SubscribeEvent
    public static void onItemStack(ItemStackedOnOtherEvent event) {
        if (!((Boolean)CommonConfig.stackFix.get()).booleanValue()) {
            return;
        }
        if (Methods.canStacksCanStack(event.getStackedOnItem(), event.getCarriedItem()) && Methods.setRadiationFromStack(event.getStackedOnItem(), event.getCarriedItem())) {
            event.setCanceled(true);
        }
    }
}

