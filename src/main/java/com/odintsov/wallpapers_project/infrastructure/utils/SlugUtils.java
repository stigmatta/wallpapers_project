package com.odintsov.wallpapers_project.infrastructure.utils;

import com.odintsov.wallpapers_project.domain.entities.BaseProduct;

import java.text.Normalizer;
import java.util.Locale;

public final class SlugUtils {

    private SlugUtils() {}

    public static void generateSlugIfMissing(BaseProduct entity) {
        if (entity.getSlug() == null && entity.getName() != null) {
            String slug = Normalizer.normalize(entity.getName(), Normalizer.Form.NFD)
                    .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                    .toLowerCase(Locale.ROOT)
                    .replaceAll("[^a-z0-9\\s-]", "")
                    .replaceAll("\\s+", "-")
                    .replaceAll("-+", "-")
                    .replaceAll("^-|-$", "");
            entity.setSlug(slug);
        }
    }
}

