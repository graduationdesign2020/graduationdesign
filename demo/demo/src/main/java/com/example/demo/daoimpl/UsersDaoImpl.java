package com.example.demo.daoimpl;

import com.example.demo.dao.UsersDao;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.Users;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsersDaoImpl implements UsersDao {
    @Autowired
    private UsersRepository usersRepository;


    @Override
    public Optional<Users> getUserByWechat_id(String wechat_id){
        return usersRepository.getByWechat_id(wechat_id);
    }

    @Override
    public Users saveUsers(Users users){
        return usersRepository.save(users);
    }

    @Override
    public int deleteUsers(String wechat_id){
        int check=usersRepository.deleteByWechat_id(wechat_id);
        return check;
    }

    @Override
    public Optional<Users> getByIdAndAuth(String id,Integer auth){
        return usersRepository.getByIdAndAuth(id, auth);
    }
}
