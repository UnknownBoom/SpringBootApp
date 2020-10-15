package com.KpApp.SpringBootApp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
@Data
@NoArgsConstructor
@Entity
@Table(name="specification_unit")
public class Specification_unit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "specification_unit_generator")
    @SequenceGenerator(name = "specification_unit_generator",sequenceName = "specification_unit_sequence" ,allocationSize = 25,initialValue = 25)
    private Long id;

    @NaturalId
    @Enumerated(EnumType.STRING)
    private Product_type product_type_id;

    @NaturalId
    @Enumerated(EnumType.STRING)
    private Product_type product_unit_id;

    @Column(nullable = false)
    private Integer amount;
}
