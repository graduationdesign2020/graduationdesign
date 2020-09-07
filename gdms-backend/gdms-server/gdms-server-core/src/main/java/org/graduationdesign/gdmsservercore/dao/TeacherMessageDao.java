package org.graduationdesign.gdmsservercore.dao;

import org.graduationdesign.gdmsservercore.entity.TeacherMessage;

import java.util.List;

public interface TeacherMessageDao {
    TeacherMessage getTeacherMessageById(Integer id);

    TeacherMessage getTeacherMessage(Integer id);

    List<TeacherMessage> getTeacherMessagesByTeacher(String teacher_id);

    void sentTeacherMessage(TeacherMessage teacherMessage);//将(title,content,teacherId)插入到message表

    List<String> getKeysById(int id);

}
