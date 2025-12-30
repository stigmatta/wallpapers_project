package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

/**
 * Entity representing a product classification or genre.
 * <p>
 * Categories are used to group products (such as wallpapers) into logical
 * collections like "Nature", "Abstract", or "Minimalist" to improve
 * searchability and user navigation.
 */
@Entity
@Table(name = TableNames.CATEGORIES)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    /**
     * Unique identifier for the category, stored as a UUID string.
     */
    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    protected String id;

    /**
     * The display name of the category (e.g., "Architecture").
     */
    @Column(name = CommonFields.NAME, nullable = false, length = 100)
    private String name;
}