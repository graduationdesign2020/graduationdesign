package org.graduationdesign.gdmsservernotice.daoimpl;

import org.graduationdesign.gdmsservernotice.dao.StudentDao;
import org.graduationdesign.gdmsservernotice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public String getDeptById(String id){
        return studentRepository.getDeptById(id);
    }
}
