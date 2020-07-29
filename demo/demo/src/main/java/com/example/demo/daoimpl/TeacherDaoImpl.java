package com.example.demo.daoimpl;

import com.example.demo.dao.TeacherDao;
import com.example.demo.entity.Teacher;
import com.example.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDaoImpl implements TeacherDao {
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher getTeacherById(String id){
        return teacherRepository.getOne(id);
    }

    @Override
    public Teacher getTeacherByIdAndName(String id,String name){
        return teacherRepository.getByIdAndName(id, name);
    }

    @Override
    public String getDeptById(String id){
        return teacherRepository.getDeptById(id);
    }
}
