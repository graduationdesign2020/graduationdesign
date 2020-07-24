package com.example.demo.daoimpl;

import com.example.demo.dao.TeacherMessageReadingDao;
import com.example.demo.entity.TeacherMessageReading;
import com.example.demo.repository.TeacherMessageReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherMessageReadingDaoImpl implements TeacherMessageReadingDao {
    @Autowired
    private TeacherMessageReadingRepository teacherMessageReadingRepository;
    @Override
    public List<TeacherMessageReading> getReading(String stu_id){
        return teacherMessageReadingRepository.getTeacherMessagesByStudent_id(stu_id);
    }

    @Override
    public TeacherMessageReading addReader(TeacherMessageReading teacherMessageReading)
    {
        return teacherMessageReadingRepository.save(teacherMessageReading);
    }

    @Override
    public int setRead(int id){
        return teacherMessageReadingRepository.setRead(id);
    }

    @Override
    public int getTeacherMessageReadingsByMessage_id(int message_id){
        return teacherMessageReadingRepository.getTeacherMessageReadingsByMessage_id(message_id);
    }

    @Override
    public int getUnReadingsByMessage_id(int message_id){
        return teacherMessageReadingRepository.getUnReadingsByMessage_id(message_id);
    }

    @Override
    public List<TeacherMessageReading> findAllByMessage(int message_id) {
        return teacherMessageReadingRepository.findAllByMessage_id(message_id);
    }

    @Override
    public List<TeacherMessageReading> findAllByMessage_id(int id){
        return teacherMessageReadingRepository.findAllByMessage_id(id);
    }
}
