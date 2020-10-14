package com.KpApp.SpringBootApp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table("product_types")
@Data
@NoArgsConstructor
public class Product_type {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "product_types_generator")
    @SequenceGenerator(name = "product_types_generator",sequenceName = "product_types_sequence" ,allocationSize = 25,initialValue = 25)
    private Long Product_type_id;

    @NaturalId(mutable = true)
    private String Product_type_name;

    @Column(nullable = false)
    private Integer dimensions;
}
