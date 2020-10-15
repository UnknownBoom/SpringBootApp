package com.KpApp.SpringBootApp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name="materials")
public class Material {
    @Id
    private String article;

    @Column(nullable = false)
    private String product_name;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private Integer amount;

    @ManyToOne
    @JoinColumn
    private Supplier main_supplier_id;

    @Column(nullable = true)
    private String image_name;

    @Enumerated(EnumType.STRING)
    private Material_type material_type;

    @Column(nullable = false)
    private BigDecimal purchase_price;

    @Column(nullable = true)
    private String gost =null;

    @Column(nullable = true)
    private Integer length =null;

    @Column(nullable = true)
    private String description =null;
}
