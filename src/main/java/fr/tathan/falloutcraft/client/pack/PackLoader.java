/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.Options
 *  net.minecraft.network.chat.Component
 *  net.minecraft.server.packs.PackResources
 *  net.minecraft.server.packs.metadata.pack.PackMetadataSection
 *  net.minecraft.server.packs.repository.Pack
 *  net.minecraft.server.packs.repository.Pack$PackConstructor
 *  net.minecraft.server.packs.repository.Pack$Position
 *  net.minecraft.server.packs.repository.PackSource
 *  net.minecraft.server.packs.repository.RepositorySource
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.fml.DistExecutor
 *  net.minecraftforge.forgespi.locating.IModFile
 *  net.minecraftforge.resource.PathPackResources
 */
package fr.tathan.falloutcraft.client.pack;

import fr.tathan.falloutcraft.client.pack.EmptyPackResources;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.forgespi.locating.IModFile;
import net.minecraftforge.resource.PathPackResources;

public class PackLoader
implements RepositorySource {
    public static final String PACK_ID = "falloutcraft.extra";
    public static final String DISABLED_PACK_ID_MARKER = "falloutcraft.extra_marker";
    private final PackResources resources;

    public PackLoader(IModFile modFile) {
        this.resources = new PathPackResources("FalloutCraft Extra", modFile.findResource(new String[]{"extra"}));
    }

    public void loadPacks(Consumer<Pack> packConsumer, Pack.PackConstructor factory) {
        Pack pack = Pack.create((String)PACK_ID, (boolean)false, () -> this.resources, (Pack.PackConstructor)factory, (Pack.Position)Pack.Position.TOP, (PackSource)PackSource.BUILT_IN);
        packConsumer.accept(pack);
        Pack marker = factory.create(DISABLED_PACK_ID_MARKER, (Component)Component.literal((String)"Halloween Mood Marker"), true, EmptyPackResources::new, new PackMetadataSection((Component)Component.empty(), 9), Pack.Position.BOTTOM, PackSource.BUILT_IN, true);
        packConsumer.accept(marker);
    }

    public static void loadOnInitialStartup() {
        DistExecutor.unsafeRunWhenOn((Dist)Dist.CLIENT, () -> () -> {
            Options options = Minecraft.getInstance().options;
            if (!options.resourcePacks.contains(DISABLED_PACK_ID_MARKER) && !options.incompatibleResourcePacks.contains(DISABLED_PACK_ID_MARKER)) {
                options.resourcePacks.add(PACK_ID);
            }
        });
    }
}

