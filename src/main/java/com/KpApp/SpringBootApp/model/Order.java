package com.KpApp.SpringBootApp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_generator")
    @SequenceGenerator(name = "order_generator",sequenceName = "Orders_sequence" ,allocationSize = 25,initialValue = 25)
    private Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date order_date;

    @Column(nullable = false,unique = true)
    private String order_name;

    @Enumerated(EnumType.STRING)
    private Product_type product_type;

    @JoinColumn(name = "customer_id",nullable = false)
    @OneToOne
    private User customer;

    @OneToOne
    @JoinColumn(name = "manager_id",nullable = true)
    private User manager = null;

    @Column(nullable = true)
    private java.math.BigDecimal price =null;

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date planed_date_end =null;

    @Column(nullable = true)
    private String order_scheme_name =null;

}
