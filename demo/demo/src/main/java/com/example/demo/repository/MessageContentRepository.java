package com.example.demo.repository;

import com.example.demo.entity.MessageContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface MessageContentRepository extends MongoRepository<MessageContent,Integer>{
    MessageContent findByMessageId(@Param("id")int id);
}



