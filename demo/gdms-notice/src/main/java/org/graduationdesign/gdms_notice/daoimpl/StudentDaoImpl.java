package org.graduationdesign.gdms_notice.daoimpl;

import org.graduationdesign.gdms_notice.dao.StudentDao;
import org.graduationdesign.gdms_notice.repository.StudentRepository;
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
