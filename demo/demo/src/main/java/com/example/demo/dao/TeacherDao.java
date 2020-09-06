package com.example.demo.dao;

import com.example.demo.entity.Teacher;

public interface TeacherDao {
    Teacher getTeacherById(String id);

    Teacher getTeacherByIdAndName(String id,String name);
}
