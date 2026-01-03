package com.odintsov.wallpapers_project.application.usecases.CreateOrder;

import com.google.cloud.firestore.Firestore;
import com.odintsov.wallpapers_project.application.dtos.OrderItem.OrderItemRequest;
import com.odintsov.wallpapers_project.application.exceptions.EntityNotFoundException;
import com.odintsov.wallpapers_project.domain.entities.ProductTypeAttribute;
import com.odintsov.wallpapers_project.domain.repositories.ExtraFeatureRepository;
import com.odintsov.wallpapers_project.domain.repositories.ProductTypeAttributeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.odintsov.wallpapers_project.infrastructure.utils.FirebaseUtils.await;

@Component
@RequiredArgsConstructor
public class CreateOrderValidator {

    private final ProductTypeAttributeRepository attributeRepository;
    private final ExtraFeatureRepository extraFeatureRepository;
    private final Firestore firestore;

    public void validate(CreateOrderCommand command) {

        if (command.items() == null || command.items().isEmpty()) {
            throw new IllegalArgumentException("Order must contain items");
        }

        command.items().forEach(item -> {
            if (item.quantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be positive");
            }
            validateItemSpecs(item);
            validateItemFeatures(item);
        });
    }

    private void validateItemSpecs(OrderItemRequest item) {
        List<ProductTypeAttribute> rules = attributeRepository.findByProductTypeId(item.productTypeId());

        var providedSpecs = item.specifications();

        providedSpecs.keySet().forEach(key -> {
            boolean isDefined = rules.stream().anyMatch(r -> r.getAttributeKey().equals(key));
            if (!isDefined) {
                throw new IllegalArgumentException("Unknown specification '" + key + "' for product type " + item.productTypeId());
            }
        });

        for (ProductTypeAttribute rule : rules) {
            String key = rule.getAttributeKey();
            Object value = providedSpecs.get(key);

            if (rule.isRequired() && (value == null)) {
                throw new IllegalArgumentException(
                        "Missing required specification: " + key + " for product type " + item.productTypeId()
                );
            }

            if (value != null) {
                validateType(key, value, rule);
            }
        }


    }

    private void validateType(String key, Object value, ProductTypeAttribute rule) {
        Map<String, Object> rules = rule.getValidationRules() != null ? rule.getValidationRules() : Collections.emptyMap();
        String type = rule.getDataType().toUpperCase();

        if (rules.containsKey("options")) {
            List<?> options = (List<?>) rules.get("options");
            if (!options.contains(value)) {
                throw new IllegalArgumentException("Invalid value for " + key + ". Allowed options: " + options);
            }
        }

        switch (type) {
            case "NUMBER" -> {
                double val;
                try {
                    if (value instanceof Number num) {
                        val = num.doubleValue();
                    } else {
                        val = Double.parseDouble(value.toString());
                    }
                } catch (NumberFormatException | NullPointerException e) {
                    throw new IllegalArgumentException("Spec " + key + " must be a valid number, but got: " + value);
                }

                if (rules.containsKey("min") && val < ((Number) rules.get("min")).doubleValue()) {
                    throw new IllegalArgumentException(key + " must be at least " + rules.get("min"));
                }
                if (rules.containsKey("max") && val > ((Number) rules.get("max")).doubleValue()) {
                    throw new IllegalArgumentException(key + " must not exceed " + rules.get("max"));
                }
                if (rules.containsKey("step")) {
                    double step = ((Number) rules.get("step")).doubleValue();
                    if (val % step != 0) {
                        throw new IllegalArgumentException(key + " must be a multiple of " + step);
                    }
                }
            }

            case "STRING" -> {
                String strVal = value.toString();
                if (rules.containsKey("pattern")) {
                    String pattern = (String) rules.get("pattern");
                    if (!strVal.matches(pattern)) {
                        throw new IllegalArgumentException("Spec " + key + " does not match required format: " + pattern);
                    }
                }
            }

            case "BOOLEAN" -> {
                if (!(value instanceof Boolean) &&
                        !(value instanceof String && ("true".equalsIgnoreCase((String) value) || "false".equalsIgnoreCase((String) value)))) {
                    throw new IllegalArgumentException("Spec " + key + " must be a boolean (true/false)");
                }
            }

            case "ENTITY_REFERENCE" -> {
                if (!(value instanceof String entityId)) {
                    throw new IllegalArgumentException("Spec " + key + " must be a string (entity ID)");
                }
                String targetCollection = (String) rules.get("collection");

                if (targetCollection != null) {
                    validateEntityExists(targetCollection, entityId);
                }
            }

            default -> System.out.println("Warning: Unknown data type " + type + " for key " + key);
        }
    }

    private void validateEntityExists(String collection, String id) {
        boolean exists = await(firestore.collection(collection).document(id).get()).exists();
        if (!exists) {
            throw new EntityNotFoundException("Referenced entity " + id + " in " + collection + " not found");
        }
    }

    private void validateItemFeatures(OrderItemRequest item) {
        if (item.features() == null) return;

        item.features().forEach(featureReq -> {
            var feature = extraFeatureRepository.findById(featureReq.featureId())
                    .orElseThrow(() -> new EntityNotFoundException("Extra feature " + featureReq.featureId() + " not found"));

            if (!feature.getProductType().getId().equals(item.productTypeId())) {
                throw new IllegalArgumentException("Feature " + feature.getName() + " is not allowed for product type " + item.productTypeId());
            }
        });
    }
}
