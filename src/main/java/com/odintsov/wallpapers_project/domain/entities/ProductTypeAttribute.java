package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.ProductFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = TableNames.PRODUCT_TYPE_ATTRIBUTES)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductTypeAttribute {

    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, length = 36, columnDefinition = "VARCHAR2(36)")
    private String id;

    /**
     * Имя атрибута, которое мы ожидаем в JSON (например, "width")
     */
    @Column(name = ProductFields.ATTRIBUTE_KEY, nullable = false)
    private String attributeKey;

    /**
     * Тип данных для базовой валидации (например, "NUMBER", "STRING", "BOOLEAN")
     */
    @Column(name = CommonFields.DATA_TYPE, nullable = false)
    private String dataType;

    /**
     * Обязателен ли этот атрибут для данного типа продукта
     */
    @Column(name = CommonFields.IS_REQUIRED, nullable = false)
    private boolean isRequired;

    /**
     * Связь с типом продукта (Обои, Краска и т.д.)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = IdFields.PRODUCT_TYPE_ID)
    private ProductType productType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Builder.Default
    private Map<String, Object> validationRules = new HashMap<>();
}
