package com.example.demo.repository;

import com.example.demo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepository extends JpaRepository<Teacher,String> {
    @Query("from Teacher where id=:id and name=:name")
    Teacher getByIdAndName(String id,String name);
}
