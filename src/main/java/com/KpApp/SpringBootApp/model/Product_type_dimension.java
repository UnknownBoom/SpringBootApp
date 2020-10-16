package com.KpApp.SpringBootApp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "Product_type_dimension")
@Data
@NoArgsConstructor
public class Product_type_dimension {

    @Id
    @Enumerated(EnumType.STRING)
    private Product_type Product_type;

    @Column(nullable = false)
    private Integer dimensions;
}
