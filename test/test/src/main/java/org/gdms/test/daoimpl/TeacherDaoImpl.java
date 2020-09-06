package org.gdms.test.daoimpl;

import org.gdms.test.dao.TeacherDao;
import org.gdms.test.entity.Teacher;
import org.gdms.test.repository.TeacherRepository;
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

    @Override
    public Teacher getTeacherById(String id){
        return teacherRepository.getOne(id);
    }

    @Override
    public Teacher getTeacherByIdAndName(String id,String name){
        return teacherRepository.getByIdAndName(id, name);
    }
}
