/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.packs.PackResources
 *  net.minecraft.server.packs.PackType
 *  net.minecraft.server.packs.metadata.MetadataSectionSerializer
 *  org.jetbrains.annotations.Nullable
 */
package fr.tathan.falloutcraft.client.pack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import org.jetbrains.annotations.Nullable;

public class EmptyPackResources
implements PackResources {
    @Nullable
    public InputStream getRootResource(String pFileName) throws IOException {
        return null;
    }

    public InputStream getResource(PackType pType, ResourceLocation pLocation) throws IOException {
        throw new FileNotFoundException();
    }

    public Collection<ResourceLocation> getResources(PackType pType, String pNamespace, String pPath, Predicate<ResourceLocation> pFilter) {
        return Collections.emptyList();
    }

    public boolean hasResource(PackType pType, ResourceLocation pLocation) {
        return false;
    }

    public Set<String> getNamespaces(PackType pType) {
        return Collections.emptySet();
    }

    @Nullable
    public <T> T getMetadataSection(MetadataSectionSerializer<T> pDeserializer) throws IOException {
        return null;
    }

    public String getName() {
        return "FalloutCraft Marker";
    }

    public void close() {
    }
}

