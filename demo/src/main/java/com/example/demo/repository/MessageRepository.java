package com.example.demo.repository;

import com.example.demo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    @Query("from Message")
    List<Message> getMessages();

    @Transactional
    @Modifying
    @Query(value = "insert into message(title,teacher_id) values(?,?)",nativeQuery = true)
    int sentMessage(String title,String teacherId);

}
