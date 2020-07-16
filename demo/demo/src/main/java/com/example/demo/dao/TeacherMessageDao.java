package com.example.demo.dao;

import com.example.demo.entity.Teacher;
import com.example.demo.entity.TeacherMessage;

import java.util.List;

public interface TeacherMessageDao {
    TeacherMessage getTeacherMessageById(Integer id);

    List<TeacherMessage> getTeacherMessages(String stu_id);

    List<TeacherMessage> getTeacherMessagesByTeacher(String teacher_id);

    void sentTeacherMessage(TeacherMessage teacherMessage);//将(title,content,teacherId)插入到message表

    int setRead(int id);//将is_read改成1

}
