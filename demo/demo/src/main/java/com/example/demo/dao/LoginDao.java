package com.example.demo.dao;

import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.Users;

public interface LoginDao {
    Users getUserByWechat_id(String wechat_id);

    Users saveUsers(Users users);

    int deleteUsers(String wechat_id);

    Student getStudentById(String id);

    Teacher getTeacherById(String id);

    Student getStudentByIdAndName(String id,String name);

    Teacher getTeacherByIdAndName(String id,String name);

    Users getByIdAndAuth(String id,Integer auth);
}
