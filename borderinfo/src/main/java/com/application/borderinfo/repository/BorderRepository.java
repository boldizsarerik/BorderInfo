package com.application.borderinfo.repository;

import com.application.borderinfo.entity.Border;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorderRepository extends JpaRepository<Border, Integer> {

    List<Border> findAll();


}

