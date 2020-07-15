package com.example.demo.daoimpl;

import com.example.demo.dao.TeacherMessageDao;
import com.example.demo.entity.SysMessage;
import com.example.demo.entity.TeacherMessage;
import com.example.demo.entity.TeacherMessageContent;
import com.example.demo.repository.SysMessageRepository;
import com.example.demo.repository.TeacherMessageContentRepository;
import com.example.demo.repository.TeacherMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherMessageDaoImpl implements TeacherMessageDao {
    @Autowired
    private TeacherMessageRepository teacherMessageRepository;
    @Autowired
    private TeacherMessageContentRepository teacherMessageContentRepository;

    @Override
    public TeacherMessage getTeacherMessageById(int id){
        TeacherMessage teacherMessage= teacherMessageRepository.getOne(id);
        TeacherMessageContent teacherMessageContent=teacherMessageContentRepository.findById(id);
        teacherMessage.setContent(teacherMessageContent.getContent());
        return teacherMessage;
    }

    @Override
    public List<TeacherMessage> getTeacherMessages(String stu_id){
        return teacherMessageRepository.getTeacherMessagesByStudent_id(stu_id);
    }

    @Override
    public void sentTeacherMessage(TeacherMessage teacherMessage){
        teacherMessageRepository.save(teacherMessage);
        TeacherMessageContent teacherMessageContent=new TeacherMessageContent(teacherMessage.getId(),teacherMessage.getContent());
        teacherMessageContentRepository.save(teacherMessageContent);
    }

    @Override
    public int setRead(int id){
        return teacherMessageRepository.setRead(id);
    }
}
