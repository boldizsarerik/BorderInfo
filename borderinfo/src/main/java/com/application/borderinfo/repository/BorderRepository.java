package com.application.borderinfo.repository;

import com.application.borderinfo.entity.Border;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BorderRepository extends JpaRepository<Border, Integer> {

    List<Border> findAll();

    @Query(value ="SELECT s FROM Border s WHERE s.name = :name ORDER BY s.date DESC")
    List<Border> findbyBorderNamebyDate(
            @Param("name") String name
    );

    Border findFirstByNameOrderByDateDesc(String name);
}

