package com.KpApp.SpringBootApp.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

public class Specification_unit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "specification_unit_generator")
    @SequenceGenerator(name = "specification_unit_generator",sequenceName = "specification_unit_sequence" ,allocationSize = 25,initialValue = 25)
    private Long id;

    @NaturalId
    @ManyToOne
    @JoinColumn
    private Product_type product_type_id;

    @NaturalId
    @ManyToOne
    @JoinColumn
    private Product_type product_unit_id;

    @Column(nullable = false)
    private Integer amount;
}
