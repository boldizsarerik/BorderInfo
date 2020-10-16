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
    private Long user_id;

    @Getter
    private String username;

    @Getter
    private String name;

    @Getter
    private int cars;

    @Getter
    private String waiting_time;

    @Getter
    private Date date;

    @Getter
    private boolean hu;

    public Border() {}

    public Border(final Long user_id,final String username,final String name, final int cars, final String waiting_time, final Date date, boolean hu) {
        this.user_id = user_id;
        this.username = username;
        this.name = name;
        this.cars = cars;
        this.waiting_time = waiting_time;
        this.date = date;
        this.hu = hu;
    }

    @Override
    public String toString() {
        return "Border{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", cars=" + cars +
                ", waiting_time='" + waiting_time + '\'' +
                ", date=" + date +
                '}';
    }
}
