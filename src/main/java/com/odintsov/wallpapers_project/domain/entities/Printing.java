package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "WSH_PRINTINGS")
public class Printing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ARTICLE")
    private String article;

    @Column(name = "REGULAR_PRICE")
    private BigDecimal regularPrice;

    @Column(name = "SALE_PRICE")
    private BigDecimal salePrice;

    @Column(name = "RATING")
    private Double rating;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "IMAGE")
    private String image;

    @Lob
    @Column(name = "GALLERY")
    private String gallery;

    @OneToMany(mappedBy = "printing", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PrintingMethodsLink> printingLinks = new ArrayList<>();

}
