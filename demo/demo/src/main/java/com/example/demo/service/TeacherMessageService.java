package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.ReadInfo;
import com.example.demo.entity.TeacherMessage;

import java.util.List;

public interface TeacherMessageService {
    TeacherMessage getTeacherMessageById(int id);

    List<TeacherMessage> getTeacherMessages(String stu_id);

    void sentTeacherMessage(String title,String teacher_id,String student_id,String content);//将(title,content,teacherId)插入到message表

    ReadInfo getTeacherMessageRead(String teacher_id);
}
