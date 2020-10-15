package com.KpApp.SpringBootApp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name="specification_operation")
public class Specification_operation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "specification_operation_generator")
    @SequenceGenerator(name = "specification_operation_generator",sequenceName = "specification_operation_sequence" ,allocationSize = 25,initialValue = 25)
    private Long id;

    @NaturalId
    @Enumerated(EnumType.STRING)
    private Product_type product_type;

    @Enumerated(EnumType.STRING)
    private Operation_type operation;

    @Enumerated(EnumType.STRING)
    private Equipment_type equipment_type;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date operation_time;
}
