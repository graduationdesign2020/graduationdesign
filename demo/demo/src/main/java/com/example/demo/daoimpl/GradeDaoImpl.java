package com.example.demo.daoimpl;

import com.example.demo.dao.GradeDao;
import com.example.demo.entity.Grade;
import com.example.demo.repository.GradeRepository;
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
