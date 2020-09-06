package org.graduatiedesign.gdmsservercore.dao;

import org.graduatiedesign.gdmsservercore.entity.TeacherMessageReading;

import java.util.List;

public interface TeacherMessageReadingDao {
    int setRead(int id);//将is_read改成1

    TeacherMessageReading addReader(TeacherMessageReading teacherMessageReading);

    int getTeacherMessageReadingsByMessage_id(int message_id);

    int getUnReadingsByMessage_id(int message_id);

    List<TeacherMessageReading> getReading(String stu_id);

    List<TeacherMessageReading> findAllByMessage_id(int id);

    List<TeacherMessageReading> findReplyByMessage_id(int id);


}
