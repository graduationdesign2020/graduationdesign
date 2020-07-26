package com.example.demo.repository;

import com.example.demo.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade,String> {
    @Query("from Grade where id=:id")
    Grade getById(String id);
}
