package com.KpApp.SpringBootApp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "equipments")
public class Equipment {
    @Id
    private String mark;

    @Enumerated(EnumType.STRING)
    private Equipment_type equipment_type;

    @Column(nullable = true)
    private String description =null;
}
