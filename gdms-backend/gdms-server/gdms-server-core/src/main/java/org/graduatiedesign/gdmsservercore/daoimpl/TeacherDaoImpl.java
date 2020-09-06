package org.graduatiedesign.gdmsservercore.daoimpl;

import org.graduatiedesign.gdmsservercore.dao.TeacherDao;
import org.graduatiedesign.gdmsservercore.entity.Teacher;
import org.graduatiedesign.gdmsservercore.repository.TeacherRepository;
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

}
