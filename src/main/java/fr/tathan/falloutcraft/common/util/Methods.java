/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 */
package fr.tathan.falloutcraft.common.util;

import fr.tathan.falloutcraft.common.config.CommonConfig;
import fr.tathan.falloutcraft.common.fluid.ModFluidTypes;
import fr.tathan.falloutcraft.common.radiation.ItemRadiation;
import fr.tathan.falloutcraft.common.radiation.ItemRadiationProvider;
import fr.tathan.falloutcraft.common.registries.EffectsRegistry;
import fr.tathan.falloutcraft.common.registries.ItemsRegistry;
import fr.tathan.falloutcraft.common.registries.TagsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;

public class Methods {
    public static final DamageSource DAMAGE_SOURCE_RADIOACTIVE_RAIN = new DamageSource("radioactive_rain").bypassArmor();

    /**
     * Slow kill clock for ambient radiation and immersion. Armor is
     * bypassed thematically (radiation goes through plate); the hazmat
     * suit gates the application itself, so it never needs to block it.
     */
    public static final DamageSource DAMAGE_SOURCE_RADIATION = new DamageSource("radiation").bypassArmor();
    public static final DamageSource DAMAGE_SOURCE_RADIATED_WATER = new DamageSource("radiated_water").bypassArmor();

    /**
     * Noise settings of the "Wastelands" world preset
     * (data/falloutcraft/worldgen/world_preset/*.json). An overworld
     * generating with these settings was created with the Falloutcraft
     * world type, which is the master switch for the radiation system.
     */
    private static final ResourceLocation WASTELANDS_NOISE_SETTINGS = new ResourceLocation("falloutcraft", "wastelands");

    /** All four Fallout biomes: the hard spatial border of ambient radiation. */
    private static final TagKey<Biome> WASTELAND_BIOMES = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation("falloutcraft", "wastedlands_biomes"));

    /**
     * The radiation system is only implemented in worlds created from the
     * Falloutcraft "Wastelands" world type (spec: absent in the default world
     * type). Derived from the save's overworld generator, so the flag is
     * save-wide - the Nether and End of an apocalyptic save count too - and
     * needs no extra state to stay in sync. Always false on the logical
     * client: every gated system is server-authoritative, effects are added
     * server-side and synced to clients.
     */
    public static boolean isRadiationWorld(Level level) {
        if (level.isClientSide) {
            return false;
        }
        MinecraftServer server = level.getServer();
        if (server == null) {
            return false;
        }
        ChunkGenerator generator = server.overworld().getChunkSource().getGenerator();
        if (!(generator instanceof NoiseBasedChunkGenerator noiseGenerator)) {
            return false;
        }
        return noiseGenerator.generatorSettings().unwrapKey().map(key -> WASTELANDS_NOISE_SETTINGS.equals(key.location())).orElse(false);
    }

    public static void addRadiation(ItemStack stack, float count) {
        ItemRadiation itemRadiation = (ItemRadiation)stack.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElse(null);
        if (itemRadiation == null) {
            return;
        }
        itemRadiation.addRadiation(count);
    }

    public static void subRadiation(ItemStack stack, float count) {
        ItemRadiation itemRadiation = (ItemRadiation)stack.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElse(null);
        if (itemRadiation == null) {
            return;
        }
        itemRadiation.subRadiation(count);
    }

    public static boolean hasHazmatSuit(Player player) {
        boolean legging = player.getItemBySlot(EquipmentSlot.LEGS).getItem().equals(ItemsRegistry.HAZMAT_LEGGINGS.get());
        boolean chestplate = player.getItemBySlot(EquipmentSlot.CHEST).getItem().equals(ItemsRegistry.HAZMAT_CHESTPLATE.get());
        boolean boots = player.getItemBySlot(EquipmentSlot.FEET).getItem().equals(ItemsRegistry.HAZMAT_BOOTS.get());
        boolean mask = player.getItemBySlot(EquipmentSlot.HEAD).getItem().equals(ItemsRegistry.HAZMAT_MASK.get());
        return legging && chestplate && boots && mask;
    }

    public static void radioactiveRain(LivingEntity entity, ResourceKey<Level> overworld) {
        if (!Methods.isLevel(entity.level, overworld)) {
            return;
        }
        if (!isRadiationWorld(entity.level)) {
            return;
        }
        if (!entity.level.getBiome(entity.blockPosition()).is(WASTELAND_BIOMES)) {
            return;
        }
        if (!((Boolean)CommonConfig.radiationRainDamage.get()).booleanValue()) {
            return;
        }
        if (entity instanceof Player) {
            Player player = (Player)entity;
            if (player.isSpectator() || player.getAbilities().instabuild) {
                return;
            }
            if (Methods.hasHazmatSuit(player)) {
                return;
            }
        }
        if (entity.getType().is(TagsRegistry.RADIATION_IMMUNISED)) {
            return;
        }
        if (entity.level.getLevelData().isRaining() && (double)entity.level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int)Math.floor(entity.getX()), (int)Math.floor(entity.getZ())) <= Math.floor(entity.getY()) + 1.0 && !entity.level.isClientSide) {
            if (entity instanceof Player) {
                // Rain carries the badge too: it marks the exposure - and
                // marks it is what suppresses natural regeneration, so the
                // slow damage below can actually add up (Events.onRadiationHeal).
                ((Player)entity).addEffect(new MobEffectInstance((MobEffect)EffectsRegistry.RADIATION.get(), 40));
            }
            if (entity.getRandom().nextFloat() < 0.002f) {
                // Kill clock: ~0.5 HP every 25 s on average = death from
                // full health in ~16 minutes of continuous rain. TATHAN's
                // 0.5-per-tick here effectively killed in seconds.
                entity.hurt(DAMAGE_SOURCE_RADIOACTIVE_RAIN, 0.5f);
            }
        }
    }

    public static boolean isLevel(Level level, ResourceKey<Level> loc) {
        return level.dimension() == loc;
    }

    public static boolean canStacksCanStack(ItemStack stack1, ItemStack stack2) {
        return stack1.getItem().equals(stack2.getItem()) && stack1.getCount() + stack2.getCount() <= stack1.getMaxStackSize();
    }

    public static boolean setRadiationFromStack(ItemStack stack1, ItemStack stack2) {
        ItemRadiation radiation1 = (ItemRadiation)stack1.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElse(null);
        ItemRadiation radiation2 = (ItemRadiation)stack2.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElse(null);
        if (radiation1 == null || radiation2 == null) {
            return false;
        }
        radiation1.loadNBTData(stack1.getOrCreateTagElement("radiation"));
        radiation2.loadNBTData(stack2.getOrCreateTagElement("radiation"));
        if (radiation2.getRadiation() > radiation1.getRadiation()) {
            // The carried stack is MORE irradiated than the target: block the
            // merge so the higher radiation level isn't lost (config:
            // "stack item with lower rad level on items with higher").
            return true;
        }
        if (radiation1.getRadiation() > radiation2.getRadiation()) {
            // Target is more irradiated: merge manually (vanilla would refuse
            // the stack anyway because the NBT differs) and cancel vanilla's
            // handling so it doesn't process the now-empty carried stack.
            stack1.setCount(stack1.getCount() + stack2.getCount());
            stack2.setCount(0);
            return true;
        }
        // Identical radiation — vanilla's own merge is fine.
        return false;
    }

    /** Hard spatial border of ambient radiation: inside one of the four Fallout biomes. */
    public static boolean isWastelandBiome(Level level, BlockPos pos) {
        return level.getBiome(pos).is(WASTELAND_BIOMES);
    }

    /**
     * Radiation intensity (0.0-1.0) at the player, driving the geiger
     * counter's click rate. Carried item rads count everywhere - items
     * are global by spec, so the counter clicks in the default world
     * type too. The ambient readings (biome, water, rain) only exist in
     * the wasteland world type. The hazmat suit blocks exposure, not
     * detection: a real geiger still clicks through protection.
     */
    public static float geigerLevel(Player player) {
        Level level = player.level;
        float reading = 0.0f;
        float carried = 0.0f;
        for (ItemStack itemStack : player.getInventory().items) {
            ItemRadiation radiation = (ItemRadiation)itemStack.getCapability(ItemRadiationProvider.ITEM_RADIATION).orElse(null);
            if (radiation == null) {
                continue;
            }
            radiation.loadNBTData(itemStack.getOrCreateTagElement("radiation"));
            carried += radiation.getRadiation();
        }
        reading += Math.min(carried / 8.0f, 0.45f);
        if (player.isInFluidType(ModFluidTypes.RADIATED_WATER_FLUID_TYPE.get())) {
            // Irradiated water is a global source (Oct 3 ruling): it
            // clicks in the default world type too.
            reading += 0.2f;
        }
        if (isRadiationWorld(level)) {
            if (isWastelandBiome(level, player.blockPosition())) {
                reading += 0.35f;
            }
            if (player.isInWater()) {
                // Tainted vanilla water - radiation world only.
                reading += 0.2f;
            }
            if (level.getLevelData().isRaining() && (double)level.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int)Math.floor(player.getX()), (int)Math.floor(player.getZ())) <= Math.floor(player.getY()) + 1.0) {
                reading += 0.25f;
            }
        }
        return Math.min(reading, 1.0f);
    }
}

