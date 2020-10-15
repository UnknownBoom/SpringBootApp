package com.KpApp.SpringBootApp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="specification_furniture")
public class Specification_furniture {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "specification_furniture_generator")
    @SequenceGenerator(name = "specification_furniture_generator",sequenceName = "specification_furniture_sequence" ,allocationSize = 25,initialValue = 25)
    private Long id;

    @NaturalId(mutable = true)
    @Enumerated(EnumType.STRING)
    private Product_type product_type;

    @NaturalId(mutable = true)
    @Enumerated(EnumType.STRING)
    private Furniture_type furniture_type;

    @Column(nullable = false)
    private Integer amount;
}
