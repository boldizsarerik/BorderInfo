package com.example.demo.domain;

import com.example.demo.repository.BorderInterface;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

// @Entity(name = "STORIES") // Ha ez lenne, akkor az adatbázisban ilyen nevü táblát hozna létre.
@Entity
public class Border
{
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    public Long id;
  //  @Column(name = "név")
  //  @Column(length = 500)
    private String name;
    private int cars;
    private Date posted;
    @ManyToOne
    private User user;

    private Border() {}

    public Border(String name, int cars, Date posted, User user)
    {
        this.name = name;
        this.cars = cars;
        this.posted = posted;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCars() {
        return cars;
    }

    public void setCars(int cars) {
        this.cars = cars;
    }

    public Date getPosted() {
        return posted;
    }

    public void setPosted(Date posted) {
        this.posted = posted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Border{" +
                "name='" + name + '\'' +
                ", cars=" + cars +
                ", posted=" + posted +
                ", user='" + user + '\'' +
                '}';
    }
}
