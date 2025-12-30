package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

/**
 * Entity representing a specific sub-category for products.
 * <p>
 * This class provides a secondary level of classification, allowing products
 * to be grouped under a specific {@link ProductType}. It is used to build
 * navigation menus and filter products in the storefront.
 */
@Entity
@Table(name = TableNames.PRODUCT_CATEGORIES)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductCategory {

    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, length = 36, columnDefinition = "VARCHAR2(36)")
    @EqualsAndHashCode.Include
    protected String id;

    /**
     * Многие записи ProductCategory могут ссылаться на одну базовую Category
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = IdFields.CATEGORY_ID)
    private Category category;

    /**
     * Многие категории (Детские, Лофт, Сканди) относятся к Одному типу (Обои)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = IdFields.PRODUCT_TYPE_ID)
    private ProductType productType;
}