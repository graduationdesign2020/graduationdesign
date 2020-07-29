package com.example.demo.dao;

import com.example.demo.entity.Student;

import java.util.List;

public interface StudentDao {
    Student getOne(String stu_id);

    Student getByName(String name);

    Student getStudentByIdAndName(String id,String name);

    List<Student> findByDept(String dept);

    String getDeptById(String id);
}
