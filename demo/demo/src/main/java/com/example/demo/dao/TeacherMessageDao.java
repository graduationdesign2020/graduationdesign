package com.example.demo.dao;

import com.example.demo.entity.TeacherMessage;
import com.example.demo.entity.TeacherMessageReading;

import java.util.List;

public interface TeacherMessageDao {
    TeacherMessage getTeacherMessageById(Integer id);

    TeacherMessage getTeacherMessage(Integer id);

    List<TeacherMessage> getTeacherMessages(String stu_id);

    List<TeacherMessage> getTeacherMessagesByTeacher(String teacher_id);

    void sentTeacherMessage(TeacherMessage teacherMessage);//将(title,content,teacherId)插入到message表

    int setRead(int id);//将is_read改成1

    List<String> getIdByTeacher_id(String t_id);

    TeacherMessageReading addReader(TeacherMessageReading teacherMessageReading);

    List<TeacherMessageReading> getReading(String stu_id);


}
