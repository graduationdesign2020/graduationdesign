package org.graduatiedesign.gdmsservercore.daoimpl;

import org.graduatiedesign.gdmsservercore.dao.GradeDao;
import org.graduatiedesign.gdmsservercore.entity.Grade;
import org.graduatiedesign.gdmsservercore.repository.GradeRepository;
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
