package com.example.demo.repository;

import com.example.demo.entity.TeacherMessageReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeacherMessageReadingRepository extends JpaRepository<TeacherMessageReading,Integer> {
    @Query("from TeacherMessageReading where student_id=:stu_id order by id desc")
    List<TeacherMessageReading> getTeacherMessagesByStudent_id(String stu_id);

    @Transactional
    @Modifying
    @Query(value = "update teachermessagereading set is_read=1 where id=?",nativeQuery=true)
    int setRead(int id);

    @Query("select count (id) from TeacherMessageReading where message_id=:message_id and is_read=true ")
    int getTeacherMessageReadingsByMessage_id(int message_id);

    @Query("select count (id) from TeacherMessageReading where message_id=:message_id and is_read=false ")
    int getUnReadingsByMessage_id(int message_id);
}