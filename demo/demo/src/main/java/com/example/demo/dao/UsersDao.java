package com.example.demo.dao;

import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.Users;

public interface UsersDao {
    Users getUserByWechat_id(String wechat_id);

    Users saveUsers(Users users);

    int deleteUsers(String wechat_id);

    Users getByIdAndAuth(String id,Integer auth);
}
