package com.example.demo.serviceimpl;

import com.example.demo.dao.*;
import com.example.demo.entity.*;
import com.example.demo.repository.TeacherMessageReadingRepository;
import com.example.demo.service.TeacherMessageService;
import com.example.demo.utils.MessageInfo;
import com.example.demo.utils.ReturnInfo;
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
    private UsersDao usersDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private TeacherMessageReadingDao teacherMessageReadingDao;
    @Autowired
    private ProjectDao projectDao;

    @Override
    public MessageInfo getTeacherMessageById(int id, int reading_id){
        TeacherMessage teacherMessage=teacherMessageDao.getTeacherMessageById(id);
        MessageInfo messageInfo=new MessageInfo();
        messageInfo.setId(teacherMessage.getId());
        messageInfo.setTitle(teacherMessage.getTitle());
        messageInfo.setContent(teacherMessage.getContent());
        messageInfo.setTime(teacherMessage.getTime());
        messageInfo.setReading_id(reading_id);
        Teacher teacher= teacherDao.getTeacherById(teacherMessage.getTeacher_id());
        messageInfo.setTeachername(teacher.getName());
        teacherMessageReadingDao.setRead(reading_id);
        return messageInfo;
    }

    @Override
    public List<MessageInfo> getTeacherMessages(String stu_id){
        List<TeacherMessageReading> list=teacherMessageReadingDao.getReading(stu_id);
        List<MessageInfo> messageInfos=new ArrayList<>();
        for (TeacherMessageReading teacherMessageReading : list) {
            MessageInfo messageInfo = new MessageInfo();
            TeacherMessage teacherMessage = teacherMessageDao.getTeacherMessage(teacherMessageReading.getMessage_id());
            messageInfo.setId(teacherMessage.getId());
            messageInfo.setReading_id(teacherMessageReading.getId());
            messageInfo.setTitle(teacherMessage.getTitle());
            messageInfo.setTime(teacherMessage.getTime());
            messageInfo.setIs_read(teacherMessageReading.getIs_read());
            Teacher teacher = teacherDao.getTeacherById(teacherMessage.getTeacher_id());
            messageInfo.setTeachername(teacher.getName());
            messageInfos.add(messageInfo);
        }
        return messageInfos;
    }

    @Override
    public ReturnInfo sentTeacherMessage(String title, String teacher_id, List<String> student_id, String content){
        Timestamp d=new Timestamp(System.currentTimeMillis());
        ReturnInfo returnInfo=new ReturnInfo();
        TeacherMessageReading s=new TeacherMessageReading();
        String time=d.toString();
        TeacherMessage teacherMessage=new TeacherMessage();
        teacherMessage.setTitle(title);
        teacherMessage.setTime(time);
        teacherMessage.setTeacher_id(teacher_id);
        teacherMessage.setContent(content);
        teacherMessageDao.sentTeacherMessage(teacherMessage);
        List<TeacherMessageReading> teacherMessageReadings=new ArrayList<>();
        for (String value : student_id) {
            TeacherMessageReading teacherMessageReading=new TeacherMessageReading();
            teacherMessageReading.setMessage_id(teacherMessage.getId());
            teacherMessageReading.setIs_read(false);
            teacherMessageReading.setStudent_id(value);
            teacherMessageReadings.add(teacherMessageReading);
        }
        for (TeacherMessageReading value:teacherMessageReadings){
            s=teacherMessageReadingDao.addReader(value);
        }
        if (s!=null)
            returnInfo.setMsg(sendingMsg1);
        else returnInfo.setMsg(sendingMsg0);
        return returnInfo;
    }

    @Override
    public ReadInfo getTeacherMessageRead(int id) {
        ReadInfo readInfo = new ReadInfo();
        //TeacherMessage teacherMessage = teacherMessageDao.getTeacherMessage(id);
        int read = 0, unread = 0;
        List<TeacherMessageReading> readings = teacherMessageReadingDao.findAllByMessage_id(id);
        List<Student> studentsRead = new ArrayList<>();
        List<Student> studentsUnread = new ArrayList<>();
        for (TeacherMessageReading teacherMessageReading : readings) {
            if (teacherMessageReading.getIs_read()) {
                read++;
                studentsRead.add(studentDao.getOne(teacherMessageReading.getStudent_id()));
            }
            else {
                unread++;
                studentsUnread.add(studentDao.getOne(teacherMessageReading.getStudent_id()));
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
        List<String> list=projectDao.getIdByTeacher_id(teacher_id);
        List<Student> students=new ArrayList<>();
        for (String s : list) {
            Student student = studentDao.getOne(s);
            students.add(student);
        }
        return students;
    }

    @Override
    public List<MessageInfo> getTeacherMessagesByTeacher_id(String t_id){
        List<TeacherMessage> list=teacherMessageDao.getTeacherMessagesByTeacher(t_id);
        List<MessageInfo> messageInfos=new ArrayList<>();
        for (TeacherMessage teacherMessage : list) {
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setTitle(teacherMessage.getTitle());
            messageInfo.setId(teacherMessage.getId());
            messageInfo.setReading(teacherMessageReadingDao.getTeacherMessageReadingsByMessage_id(teacherMessage.getId()));
            messageInfo.setUnread(teacherMessageReadingDao.getUnReadingsByMessage_id(teacherMessage.getId()));
            messageInfo.setTime(teacherMessage.getTime());
            messageInfos.add(messageInfo);
        }
        return messageInfos;
    }

    @Override
    public MessageInfo teacherGetTeacherMessageById(int id){
        TeacherMessage teacherMessage=teacherMessageDao.getTeacherMessageById(id);
        MessageInfo messageInfo=new MessageInfo();
        messageInfo.setId(teacherMessage.getId());
        messageInfo.setTitle(teacherMessage.getTitle());
        messageInfo.setContent(teacherMessage.getContent());
        messageInfo.setTime(teacherMessage.getTime());
        Teacher teacher= teacherDao.getTeacherById(teacherMessage.getTeacher_id());
        messageInfo.setTeachername(teacher.getName());
        return messageInfo;
    }

}
