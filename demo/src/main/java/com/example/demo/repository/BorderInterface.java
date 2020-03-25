package com.example.demo.repository;

import com.example.demo.domain.Border;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorderInterface extends CrudRepository<Border,Long>
{
    List<Border> findAll(); // Ez lényegében egy : SELECT * FROM SQL utasítás.

    // SELECT * FROM BORDER WHERE POSTED IN (SELECT MAX(POSTED) FROM BORDER) LIMIT 1;
    Border findFirstByOrderByPostedDesc(); // Ez egyetlen határt ad vissza.

    //Border findByName(String name);
    //@Query(value = "select * from border where name = ?1 limit 1", nativeQuery = true)
    //@Query(value = "select * from border where name = :name limit 1", nativeQuery = true)
    @Query(value = "select s from Border s where s.name = :name")
    Border findByName(@Param("name") String name);

    List<Border> findAllByUserUsernameIgnoreCaseOrderByPostedDesc(String name);

}
