package org.graduationdesign.gdmsservernotice.daoimpl;

import org.graduationdesign.gdmsservernotice.dao.TeacherDao;
import org.graduationdesign.gdmsservernotice.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDaoImpl implements TeacherDao {
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public String getDeptById(String id){
        return teacherRepository.getDeptById(id);
    }
}
