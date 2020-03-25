package com.example.demo.services;

import com.example.demo.domain.Border;
import com.example.demo.domain.User;
import com.example.demo.repository.BorderInterface;
import com.example.demo.repository.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Service
public class Services
{
    private BorderInterface borderRepo;
    private UserInterface userRepo;

    @Autowired
    public void setBorderRepo(BorderInterface borderRepo)
    {
        this.borderRepo = borderRepo;
    }
    @Autowired
    public void setUserRepo(UserInterface userRepo)
    {
        this.userRepo = userRepo;
    }
    public List<Border> getBorders()
    {
        return borderRepo.findAll();
    }
    public Border getBorder()
    {
        return borderRepo.findFirstByOrderByPostedDesc();
    }

    public Border getSpecificBorder(String name)
    {
        return borderRepo.findByName(name);
    }

    public List<Border> getBordersByUserName(String name) {
        return borderRepo.findAllByUserUsernameIgnoreCaseOrderByPostedDesc(name);
    }

  /*  @PostConstruct // Ez arra kell, hogy egyetlen egyszer, de a legelején lefusson az init() metódus.
    public void init()
    {
        User felhasznalo = new User("Szántyó Györgyi","12345678");
        userRepo.save(felhasznalo);
        // Azt a felhasználót adjuk át, amelyet az előbb létrehoztunk, a belsőt, ezzel összekötöttük őket, ezután kell elmenteni.
        Border hatar = new Border("Barabás -> Kaszony",4,new Date(),felhasznalo);
        borderRepo.save(hatar);
    }*/
}
