package com.example.demo.repository;

import com.example.demo.entity.TeacherMessageContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeacherMessageContentRepository extends MongoRepository<TeacherMessageContent,Integer>{
    Optional<TeacherMessageContent> findById(@Param("_id")int id);

}



