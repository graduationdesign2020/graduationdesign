package com.example.demo.serviceimpl;

import com.example.demo.dao.TeacherMessageDao;
import com.example.demo.entity.TeacherMessage;
import com.example.demo.service.TeacherMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TeacherMessageServiceImpl implements TeacherMessageService {
    @Autowired
    private TeacherMessageDao teacherMessageDao;

    @Override
    public TeacherMessage getTeacherMessageById(int id){
        teacherMessageDao.setRead(id);
        return teacherMessageDao.getTeacherMessageById(id);
    }

    @Override
    public List<TeacherMessage> getTeacherMessages(String stu_id){
        return teacherMessageDao.getTeacherMessages(stu_id);
    }

    @Override
    public void sentTeacherMessage(String title,String teacher_id,String student_id,String content){
        Timestamp d=new Timestamp(System.currentTimeMillis());
        String time=d.toString();
        TeacherMessage teacherMessage=new TeacherMessage(title,teacher_id,student_id,time,content);
        teacherMessageDao.sentTeacherMessage(teacherMessage);
    }

}
