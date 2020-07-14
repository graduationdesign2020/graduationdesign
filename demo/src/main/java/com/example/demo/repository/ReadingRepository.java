package com.example.demo.repository;

import com.example.demo.entity.Reading;
import com.example.demo.entity.ReadingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ReadingRepository extends JpaRepository<Reading, ReadingKey> {
    @Transactional
    @Modifying
    @Query(value = "insert into reading(message_id,student_id) values(?,?)",nativeQuery = true)
    int addReader(int messageId,String studentId);

    @Transactional
    @Modifying
    @Query(value = "update reading set is_read=1 where message_id=? and student_id=?",nativeQuery = true)
    int haveRead(int messageId,String studentId);

}