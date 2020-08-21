package org.graduationdesign.gdms_notice.daoimpl;

import org.graduationdesign.gdms_notice.dao.TeacherDao;
import org.graduationdesign.gdms_notice.repository.TeacherRepository;
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
