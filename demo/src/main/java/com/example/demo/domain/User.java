package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User
{
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;
    private String username;
    private String password;
    @JsonBackReference
    @OneToMany(mappedBy = "user")
    private List<Border> bordersList;

    public User() {}

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Border> getBordersList() {
        return bordersList;
    }

    public void setBordersList(List<Border> bordersList) {
        this.bordersList = bordersList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", bordersList=" + bordersList +
                '}';
    }
}
