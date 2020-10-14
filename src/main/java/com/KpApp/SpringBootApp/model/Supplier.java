package com.KpApp.SpringBootApp.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table("suppliers")
@Data
@NoArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "suppliers_generator")
    @SequenceGenerator(name = "suppliers_generator",sequenceName = "suppliers_sequence" ,allocationSize = 25,initialValue = 25)
    private Long supplier_id;

    @NaturalId(mutable = true)
    @Column(name = "supplier_name")
    private String supplier_name;

    @Column(nullable = true)
    private String address =null;

    @Column(nullable = false)
    private Integer delivery_period;
}
