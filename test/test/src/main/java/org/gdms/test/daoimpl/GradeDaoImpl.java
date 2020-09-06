package org.gdms.test.daoimpl;

import org.gdms.test.dao.GradeDao;
import org.gdms.test.entity.Grade;
import org.gdms.test.repository.GradeRepository;
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
