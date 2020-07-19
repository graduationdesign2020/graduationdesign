package com.example.demo.repository;

import com.example.demo.entity.TeacherMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeacherMessageRepository extends JpaRepository<TeacherMessage,Integer> {
//    @Query("from TeacherMessage where student_id=:stu_id order by time desc")
//    List<TeacherMessage> getTeacherMessagesByStudent_id(String stu_id);

    List<TeacherMessage> findAllByTeacher_id(String teacher_id);

    @Transactional
    @Modifying
    @Query(value = "update teachermessage set is_read=1 where id=?",nativeQuery=true)
    int setRead(int id);
}
