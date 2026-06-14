package io.github.thebusybiscuit.exoticgarden;

import javax.annotation.Nonnull;

import org.bukkit.Material;

import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;

/**
 * Resolves {@link XMaterial} constants to a {@link Material} that exists on the running server.
 * Keeps ExoticGarden loadable on legacy versions (e.g. 1.8) where modern flattening constants like
 * {@code PLAYER_HEAD}, {@code OAK_SAPLING} or {@code SWEET_BERRIES} are absent from the {@link Material}
 * enum and a direct reference would throw {@link NoSuchFieldError}.
 *
 * @author TheBusyBiscuit
 */
public final class MaterialCompat {

    private MaterialCompat() {}

    @Nonnull
    public static Material safe(@Nonnull XMaterial material) {
        Material resolved = material.parseMaterial();
        return resolved != null ? resolved : Material.STONE;
    }
}
