package com.example.demo.daoimpl;

import com.example.demo.dao.LoginDao;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.Users;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDaoImpl implements LoginDao {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Users getUserByWechat_id(String wechat_id){
        return usersRepository.getByWechat_id(wechat_id);
    }

    @Override
    public Users saveUsers(Users users){
        return usersRepository.save(users);
    }

    @Override
    public int deleteUsers(String wechat_id){
        usersRepository.deleteById(wechat_id);
        return 1;
    }

    @Override
    public Student getStudentById(String id){
        return studentRepository.getOne(id);
    }

    @Override
    public Teacher getTeacherById(String id){
        return teacherRepository.getOne(id);
    }

    @Override
    public Student getStudentByIdAndName(String id,String name){
        return studentRepository.getByIdAndName(id, name);
    }

    @Override
    public Teacher getTeacherByIdAndName(String id,String name){
        return teacherRepository.getByIdAndName(id, name);
    }

    @Override
    public Users getByIdAndAuth(String id,Integer auth){
        return usersRepository.getByIdAndAuth(id, auth);
    }
}
