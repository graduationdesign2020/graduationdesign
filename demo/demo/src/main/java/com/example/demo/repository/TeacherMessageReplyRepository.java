package com.example.demo.repository;

import com.example.demo.entity.TeacherMessageReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeacherMessageReplyRepository extends JpaRepository<TeacherMessageReply,Integer> {
    @Query("from TeacherMessageReply where student_id=:stu_id order by id desc")
    List<TeacherMessageReply> getReplyMessagesByStudent_id(String stu_id);

    @Transactional
    @Modifying
    @Query(value = "update teachermessagereply set is_reply=1 where id=?",nativeQuery=true)
    int setReply(int id);

    @Query("select count (id) from TeacherMessageReply where message_id=:message_id and is_reply=true ")
    int getRepliesByMessage_id(int message_id);

    @Query("select count (id) from TeacherMessageReply where message_id=:message_id and is_reply=false ")
    int getUnRepliesByMessage_id(int message_id);

    @Query("from TeacherMessageReply where message_id=:id")
    List<TeacherMessageReply> findAllByMessage_id(int id);
}
