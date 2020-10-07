package com.application.borderinfo.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Border {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private int cars;

    @Getter
    private String waiting_time;

    @Getter
    private Date date;

    public Border() {}

    public Border(final String name, final int cars, final String waiting_time, final Date date) {
        this.name = name;
        this.cars = cars;
        this.waiting_time = waiting_time;
        this.date = date;
    }
}
