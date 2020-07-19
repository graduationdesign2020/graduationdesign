package com.example.demo.repository;

import com.example.demo.entity.TeacherMessageContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface TeacherMessageContentRepository extends MongoRepository<TeacherMessageContent,Integer>{
    TeacherMessageContent findById(@Param("_id")int id);
}



