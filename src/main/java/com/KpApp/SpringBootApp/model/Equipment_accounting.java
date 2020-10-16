package com.KpApp.SpringBootApp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
public class Equipment_accounting {
    @Id
    private String mark;

    @Enumerated(EnumType.STRING)
    private Equipment_type equipment_type;

    @Temporal(TemporalType.DATE)
    private Date begin_date;

    private Integer deterioration;

    @Temporal(TemporalType.DATE)
    private Date end_date;

}
