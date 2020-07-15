package com.example.demo.dao;

import com.example.demo.entity.TeacherMessage;

import java.util.List;

public interface TeacherMessageDao {
    TeacherMessage getTeacherMessageById(int id);

    List<TeacherMessage> getTeacherMessages(String stu_id);

    void sentTeacherMessage(TeacherMessage teacherMessage);//将(title,content,teacherId)插入到message表

    int setRead(int id);//将is_read改成1

}
