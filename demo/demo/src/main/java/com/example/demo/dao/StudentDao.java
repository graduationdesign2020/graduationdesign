package com.example.demo.dao;

import com.example.demo.entity.Student;

public interface StudentDao {
    Student getOne(String stu_id);

    Student getByName(String name);

    Student getStudentByIdAndName(String id,String name);

}
