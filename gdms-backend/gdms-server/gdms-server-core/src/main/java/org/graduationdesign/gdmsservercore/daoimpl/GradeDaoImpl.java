package org.graduationdesign.gdmsservercore.daoimpl;

import org.graduationdesign.gdmsservercore.dao.GradeDao;
import org.graduationdesign.gdmsservercore.entity.Grade;
import org.graduationdesign.gdmsservercore.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GradeDaoImpl implements GradeDao {
    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public Grade getById(String id){
        return gradeRepository.getById(id);
    }
}
