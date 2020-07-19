package com.example.demo.daoimpl;

import com.example.demo.dao.TeacherMessageDao;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TeacherMessageDaoImpl implements TeacherMessageDao {
    @Autowired
    private TeacherMessageRepository teacherMessageRepository;
    @Autowired
    private TeacherMessageContentRepository teacherMessageContentRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TeacherMessageReadingRepository teacherMessageReadingRepository;

    @Override
    public TeacherMessage getTeacherMessageById(Integer id){
        TeacherMessage teacherMessage= teacherMessageRepository.getOne(id);
        Optional<TeacherMessageContent> teacherMessageContent=teacherMessageContentRepository.findById(id);
        if (teacherMessageContent.isPresent()){
            TeacherMessageContent s=teacherMessageContent.get();
            teacherMessage.setContent(s.getContent());
        }
        else{
            teacherMessage.setContent(null);
        }
        return teacherMessage;
    }

    @Override
    public TeacherMessage getTeacherMessage(Integer id){
        return teacherMessageRepository.getOne(id);
    }

    @Override
    public List<TeacherMessageReading> getReading(String stu_id){
        return teacherMessageReadingRepository.getTeacherMessagesByStudent_id(stu_id);
    }

//    @Override
//    public List<TeacherMessage> getTeacherMessages(String stu_id){
//        return teacherMessageRepository.getTeacherMessagesByStudent_id(stu_id);
//    }

    @Override
    public List<TeacherMessage> getTeacherMessagesByTeacher(String teacher_id) {
        return teacherMessageRepository.findAllByTeacher_id(teacher_id);
    }

    @Override
    public void sentTeacherMessage(TeacherMessage teacherMessage){
        teacherMessageRepository.save(teacherMessage);
        TeacherMessageContent teacherMessageContent=new TeacherMessageContent(teacherMessage.getId(),teacherMessage.getContent());
        teacherMessageContentRepository.save(teacherMessageContent);
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
    public List<String> getIdByTeacher_id(String t_id){
        return projectRepository.getIdByTeacher_id(t_id);
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


}
