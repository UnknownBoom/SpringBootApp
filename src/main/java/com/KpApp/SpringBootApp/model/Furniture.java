package com.KpApp.SpringBootApp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "furnitures")
public class Furniture {
    @Id
    private String article;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private Integer amount;

    @JoinColumn(nullable = true)
    @ManyToOne
    private Supplier main_supplier;

    @Enumerated(EnumType.STRING)
    private Furniture_type furniture_type;

    @Column(nullable = false)
    private BigDecimal purchase_price;

    @Column(nullable = false)
    private Integer weight;

    @Column(nullable = true)
    private String image_name;
}
