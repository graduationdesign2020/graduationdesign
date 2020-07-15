package com.example.demo.repository;

import com.example.demo.entity.NoticeContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface NoticeContentRepository extends MongoRepository<NoticeContent,Integer> {
    NoticeContent findByNoticeId(@Param("id")int id);
}
