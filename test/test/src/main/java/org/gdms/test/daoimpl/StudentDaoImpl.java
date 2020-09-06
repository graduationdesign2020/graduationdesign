package org.gdms.test.daoimpl;

import org.gdms.test.dao.StudentDao;
import org.gdms.test.entity.Student;
import org.gdms.test.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public String getDeptById(String id){
        return studentRepository.getDeptById(id);
    }

    @Override
    public Student getOne(String stu_id) {
        return studentRepository.getOne(stu_id);
    }

    @Override
    public Student getStudentByIdAndName(String id,String name){
        Student student = studentRepository.getByIdAndName(id, name);
        return student;
    }

    @Override
    public List<Student> findByDept(String dept) {
        return studentRepository.findAllByDepartment(dept);
    }
}
