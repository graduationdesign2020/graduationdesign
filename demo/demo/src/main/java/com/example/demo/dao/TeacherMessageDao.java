package com.example.demo.dao;

import com.example.demo.entity.TeacherMessage;
import com.example.demo.entity.TeacherMessageReading;

import java.util.List;
import java.util.Optional;

public interface TeacherMessageDao {
    TeacherMessage getTeacherMessageById(Integer id);

    TeacherMessage getTeacherMessage(Integer id);


    List<TeacherMessage> getTeacherMessagesByTeacher(String teacher_id);

    void sentTeacherMessage(TeacherMessage teacherMessage);//将(title,content,teacherId)插入到message表
}
