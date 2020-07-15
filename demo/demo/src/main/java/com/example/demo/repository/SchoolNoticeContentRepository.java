package com.example.demo.repository;

import com.example.demo.entity.SchoolNoticeContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface SchoolNoticeContentRepository extends MongoRepository<SchoolNoticeContent,Integer> {
    SchoolNoticeContent findById(@Param("id")int id);
}
