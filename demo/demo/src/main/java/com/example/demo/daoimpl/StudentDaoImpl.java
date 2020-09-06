package com.example.demo.daoimpl;

import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student getOne(String stu_id) {
        return studentRepository.getOne(stu_id);
    }

    @Override
    public Student getStudentByIdAndName(String id,String name){
        return studentRepository.getByIdAndName(id, name);
    }

    @Override
    public List<Student> findByDept(String dept) {
        return studentRepository.findAllByDepartment(dept);
    }
}
