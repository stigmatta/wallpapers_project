package com.odintsov.wallpapers_project.infrastructure.utils;

import com.odintsov.wallpapers_project.domain.repositories.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class ProductTypeRegistry {

    private final ProductTypeRepository productTypeRepository;
    private final Map<String, String> typeCache = new ConcurrentHashMap<>();

    @EventListener(ContextRefreshedEvent.class)
    public void init() {
        refresh();
    }

    /**
     * Позволяет принудительно обновить кэш после работы Initializers
     */
    public void refresh() {
        productTypeRepository.findAll().forEach(type -> {
            typeCache.put(type.getName(), type.getId());
        });
    }

    public String getTypeId(String typeName) {
        if (typeCache.isEmpty()) {
            refresh();
        }

        String id = typeCache.get(typeName);

        if (id == null) {
            return productTypeRepository.findAll().stream()
                    .filter(t -> t.getName().equals(typeName))
                    .findFirst()
                    .map(t -> {
                        typeCache.put(t.getName(), t.getId());
                        return t.getId();
                    })
                    .orElseThrow(() -> new RuntimeException("Тип продукта " + typeName + " не найден в БД!"));
        }
        return id;
    }
}
