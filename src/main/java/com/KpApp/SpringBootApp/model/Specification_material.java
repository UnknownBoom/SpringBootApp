package com.KpApp.SpringBootApp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="specification_material")
public class Specification_material {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "specification_material_generator")
    @SequenceGenerator(name = "specification_material_generator",sequenceName = "specification_materials_sequence" ,allocationSize = 25,initialValue = 25)
    private Long id;

    @NaturalId
    @Enumerated(EnumType.STRING)
    private Product_type product_type;

    @NaturalId
    @ManyToOne
    @JoinColumn
    private Material material;

    @Column(nullable = false)
    private Integer amount;
}
