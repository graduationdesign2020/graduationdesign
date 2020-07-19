package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.ReadInfo;
import com.example.demo.entity.Student;
import com.example.demo.entity.TeacherMessage;
import com.example.demo.utils.MessageInfo;
import com.example.demo.utils.ReturnInfo;

import java.util.List;

public interface TeacherMessageService {
    MessageInfo getTeacherMessageById(int id, int reading_id);

    MessageInfo teacherGetTeacherMessageById(int id);

    List<MessageInfo> getTeacherMessages(String stu_id);

    ReturnInfo sentTeacherMessage(String title, String teacher_id, List<String> student_id, String content);//将(title,content,teacherId)插入到message表

    ReadInfo getTeacherMessageRead(String teacher_id);

    List<Student> getStudentsByTeacher_id(String teacher_id);

    List<MessageInfo> getTeacherMessagesByTeacher_id(String t_id);
}
