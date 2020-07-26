package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("from Student where id=:id and name=:name")
    Student getByIdAndName(String id,String name);

    Student findDistinctByName(String name);

    List<Student> findAllByDepartment(String dept);
}
