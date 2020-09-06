package org.graduatiedesign.gdmsservercore.service;

import org.graduatiedesign.gdmsservercore.entity.Student;
import org.graduatiedesign.gdmsservercore.utils.MessageInfo;
import org.graduatiedesign.gdmsservercore.utils.ReadInfo;
import org.graduatiedesign.gdmsservercore.utils.ReturnInfo;

import java.util.List;

public interface TeacherMessageService {
    MessageInfo getTeacherMessageById(int id, int reading_id);

    MessageInfo teacherGetTeacherMessageById(int id);

    List<MessageInfo> getTeacherMessages(String stu_id);

    ReturnInfo sentTeacherMessage(String title, String teacher_id, List<String> student_id, String content,List<String> tasks);//将(title,content,teacherId)插入到message表

    ReadInfo getTeacherMessageRead(int id);

    List<Student> getStudentsByTeacher_id(String teacher_id);

    List<MessageInfo> getTeacherMessagesByTeacher_id(String t_id);
}
