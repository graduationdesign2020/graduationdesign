package com.example.demo.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.StudentDao;
import com.example.demo.dao.TeacherMessageDao;
import com.example.demo.entity.ReadInfo;
import com.example.demo.entity.Student;
import com.example.demo.entity.TeacherMessage;
import com.example.demo.service.TeacherMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherMessageServiceImpl implements TeacherMessageService {
    @Autowired
    private TeacherMessageDao teacherMessageDao;
    @Autowired
    private StudentDao studentDao;

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
        TeacherMessage teacherMessage=new TeacherMessage();
        teacherMessage.setTitle(title);
        teacherMessage.setStudent_id(student_id);
        teacherMessage.setTime(time);
        teacherMessage.setTeacher_id(teacher_id);
        teacherMessage.setIs_read(false);
        teacherMessage.setContent(content);
        teacherMessageDao.sentTeacherMessage(teacherMessage);
    }

    @Override
    public ReadInfo getTeacherMessageRead(String teacher_id) {
        ReadInfo readInfo = new ReadInfo();
        List<TeacherMessage> teacherMessages = teacherMessageDao.getTeacherMessagesByTeacher(teacher_id);
        int read = 0, unread = 0;
        List<Student> studentsRead = new ArrayList<>();
        List<Student> studentsUnread = new ArrayList<>();
        for (TeacherMessage teacherMessage : teacherMessages) {
            if (teacherMessage.getIs_read()) {
                read++;
                studentsRead.add(studentDao.getOne(teacherMessage.getStudent_id()));
            }
            else {
                unread++;
                studentsUnread.add(studentDao.getOne(teacherMessage.getStudent_id()));
            }
        }
        readInfo.setRead(read);
        readInfo.setUnRead(unread);
        readInfo.setStudentsRead(studentsRead);
        readInfo.setStudentsUnread(studentsUnread);
        return readInfo;
    }

}
