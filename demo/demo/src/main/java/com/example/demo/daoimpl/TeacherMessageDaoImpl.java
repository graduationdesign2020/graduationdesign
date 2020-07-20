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

    @Override
    public Optional<TeacherMessage> getTeacherMessageById(Integer id){
       return teacherMessageRepository.getById(id);

    }

    @Override
    public TeacherMessage getTeacherMessage(Integer id){
        return teacherMessageRepository.getOne(id);
    }



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
}
