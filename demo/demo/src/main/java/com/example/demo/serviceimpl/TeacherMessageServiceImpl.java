package com.example.demo.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.LoginDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.dao.TeacherMessageDao;
import com.example.demo.entity.*;
import com.example.demo.service.TeacherMessageService;
import com.example.demo.utils.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.constant.ReturnMsg.sendingMsg0;
import static com.example.demo.constant.ReturnMsg.sendingMsg1;

@Service
public class TeacherMessageServiceImpl implements TeacherMessageService {
    @Autowired
    private TeacherMessageDao teacherMessageDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private LoginDao loginDao;

    @Override
    public MessageInfo getTeacherMessageById(int id, int reading_id){
        TeacherMessage teacherMessage=teacherMessageDao.getTeacherMessageById(id);
        MessageInfo messageInfo=new MessageInfo();
        messageInfo.setId(teacherMessage.getId());
        messageInfo.setTitle(teacherMessage.getTitle());
        messageInfo.setContent(teacherMessage.getContent());
        messageInfo.setTime(teacherMessage.getTime());
        messageInfo.setReading_id(reading_id);
        Teacher teacher=loginDao.getTeacherById(teacherMessage.getTeacher_id());
        messageInfo.setTeachername(teacher.getName());
        teacherMessageDao.setRead(reading_id);
        return messageInfo;
    }

    @Override
    public List<MessageInfo> getTeacherMessages(String stu_id){
        List<TeacherMessageReading> list=teacherMessageDao.getReading(stu_id);
        List<MessageInfo> messageInfos=new ArrayList<>();
        for (TeacherMessageReading teacherMessageReading : list) {
            MessageInfo messageInfo = new MessageInfo();
            TeacherMessage teacherMessage = teacherMessageDao.getTeacherMessage(teacherMessageReading.getMessage_id());
            messageInfo.setId(teacherMessage.getId());
            messageInfo.setReading_id(teacherMessageReading.getId());
            messageInfo.setTitle(teacherMessage.getTitle());
            messageInfo.setTime(teacherMessage.getTime());
            messageInfo.setIs_read(teacherMessageReading.getIs_read());
            Teacher teacher = loginDao.getTeacherById(teacherMessage.getTeacher_id());
            messageInfo.setTeachername(teacher.getName());
            messageInfos.add(messageInfo);
        }
        return messageInfos;
    }

    @Override
    public String sentTeacherMessage(String title, String teacher_id, String student_id, String content){
        Timestamp d=new Timestamp(System.currentTimeMillis());
        String time=d.toString();
        TeacherMessage teacherMessage=new TeacherMessage();
        teacherMessage.setTitle(title);
        teacherMessage.setTime(time);
        teacherMessage.setTeacher_id(teacher_id);
        teacherMessage.setContent(content);
        teacherMessageDao.sentTeacherMessage(teacherMessage);
        TeacherMessageReading teacherMessageReading=new TeacherMessageReading();
        teacherMessageReading.setMessage_id(teacherMessage.getId());
        teacherMessageReading.setStudent_id(student_id);
        teacherMessageReading.setIs_read(false);
        TeacherMessageReading s=teacherMessageDao.addReader(teacherMessageReading);
        if (s!=null)
            return sendingMsg1;
        else return sendingMsg0;
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

    @Override
    public List<Student> getStudentsByTeacher_id(String teacher_id){
        List<String> list=teacherMessageDao.getIdByTeacher_id(teacher_id);
        List<Student> students=new ArrayList<>();
        for (String s : list) {
            Student student = loginDao.getStudentById(s);
            students.add(student);
        }
        return students;
    }

    @Override
    public List<TeacherMessage> getTeacherMessagesByTeacher_id(String t_id){
        return teacherMessageDao.getTeacherMessagesByTeacher(t_id);
    }

    @Override
    public MessageInfo teacherGetTeacherMessageById(int id){
        TeacherMessage teacherMessage=teacherMessageDao.getTeacherMessageById(id);
        MessageInfo messageInfo=new MessageInfo();
        messageInfo.setId(teacherMessage.getId());
        messageInfo.setTitle(teacherMessage.getTitle());
        messageInfo.setContent(teacherMessage.getContent());
        messageInfo.setTime(teacherMessage.getTime());
        Teacher teacher=loginDao.getTeacherById(teacherMessage.getTeacher_id());
        messageInfo.setTeachername(teacher.getName());
        return messageInfo;
    }

}
