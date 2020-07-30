package com.example.demo.dao;

import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.Users;

import java.util.Optional;

public interface UsersDao {
    Optional<Users> getUserByWechat_id(String wechat_id);

    Users saveUsers(Users users);

    int deleteUsers(String wechat_id);

    Optional<Users> getByIdAndAuth(String id,String auth);

    Optional<Users> getById(String id);
}
