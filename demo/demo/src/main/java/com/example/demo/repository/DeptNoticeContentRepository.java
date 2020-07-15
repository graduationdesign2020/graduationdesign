package com.example.demo.repository;

import com.example.demo.entity.DeptNoticeContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface DeptNoticeContentRepository extends MongoRepository<DeptNoticeContent,Integer> {
    DeptNoticeContent findById(@Param("id")int id);
}
