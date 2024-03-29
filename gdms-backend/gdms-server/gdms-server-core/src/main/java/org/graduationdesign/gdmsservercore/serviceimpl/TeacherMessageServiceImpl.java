package org.graduationdesign.gdmsservercore.serviceimpl;

import org.graduationdesign.gdmsservercore.dao.*;
import org.graduationdesign.gdmsservercore.entity.*;
import org.graduationdesign.gdmsservercore.service.TeacherMessageService;
import org.graduationdesign.gdmsservercore.utils.MessageInfo;
import org.graduationdesign.gdmsservercore.utils.ReadInfo;
import org.graduationdesign.gdmsservercore.utils.ReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.graduationdesign.gdmsservercore.constant.ReturnMsg.Msg0;
import static org.graduationdesign.gdmsservercore.constant.ReturnMsg.Msg1;

@Service
public class TeacherMessageServiceImpl implements TeacherMessageService {
    @Autowired
    private TeacherMessageDao teacherMessageDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private TeacherMessageReadingDao teacherMessageReadingDao;
    @Autowired
    private ProjectDao projectDao;

    @Override
    public MessageInfo getTeacherMessageById(int id, int reading_id){
        TeacherMessage t=teacherMessageDao.getTeacherMessageById(id);
        if(t!=null)
        {
            MessageInfo messageInfo=new MessageInfo();
            messageInfo.setId(t.getId());
            messageInfo.setType(0);
            messageInfo.setTitle(t.getTitle());
            messageInfo.setContent(t.getTeacherMessageContent().getContent());
            messageInfo.setTime(t.getTime());
            messageInfo.setReading_id(reading_id);
            Teacher teacher= teacherDao.getTeacherById(t.getTeacher_id());
            messageInfo.setTeachername(teacher.getName());
            teacherMessageReadingDao.setRead(reading_id);
            return messageInfo;
        }
        else {
            return null;
        }
    }

    @Override
    public List<MessageInfo> getTeacherMessages(String stu_id){
        List<TeacherMessageReading> list=teacherMessageReadingDao.getReading(stu_id);
        List<MessageInfo> messageInfos=new ArrayList<>();
        for (TeacherMessageReading teacherMessageReading : list) {
            MessageInfo messageInfo = new MessageInfo();
            TeacherMessage teacherMessage = teacherMessageDao.getTeacherMessage(teacherMessageReading.getMessage_id());
            messageInfo.setId(teacherMessage.getId());
            messageInfo.setType(teacherMessage.getType());
            messageInfo.setReading_id(teacherMessageReading.getId());
            messageInfo.setTitle(teacherMessage.getTitle());
            messageInfo.setTime(teacherMessage.getTime());
            messageInfo.setIsread(teacherMessageReading.is_read());
            Teacher teacher = teacherDao.getTeacherById(teacherMessage.getTeacher_id());
            messageInfo.setTeachername(teacher.getName());
            messageInfos.add(messageInfo);
        }
        return messageInfos;
    }

    @Override
    public ReturnInfo sentTeacherMessage(String title, String teacher_id, List<String> student_id, String content, List<String> tasks){
        Timestamp d=new Timestamp(System.currentTimeMillis());
        ReturnInfo returnInfo=new ReturnInfo();
        TeacherMessageReading s=new TeacherMessageReading();
        String time=d.toString();
        TeacherMessage teacherMessage=new TeacherMessage();
        teacherMessage.setTitle(title);
        teacherMessage.setTime(time);
        teacherMessage.setTeacher_id(teacher_id);
        TeacherMessageContent teacherMessageContent=new TeacherMessageContent();
        teacherMessageContent.setTitle(title);
        teacherMessageContent.setContent(content);
        teacherMessageContent.setStudents(student_id);
        teacherMessageContent.setType(2);
        teacherMessage.setTeacherMessageContent(teacherMessageContent);
        if(tasks==null||tasks.size()<=0) {
            teacherMessage.setType(0);
        }
        else {
            teacherMessage.setType(1);
            teacherMessageContent.setKeys(tasks);
        }
        teacherMessageDao.sentTeacherMessage(teacherMessage);
        List<TeacherMessageReading> teacherMessageReadings=new ArrayList<>();
        for (String value : student_id) {
            TeacherMessageReading teacherMessageReading=new TeacherMessageReading();
            teacherMessageReading.setMessage_id(teacherMessage.getId());
            teacherMessageReading.set_read(false);
            teacherMessageReading.setStudent_id(value);
            teacherMessageReadings.add(teacherMessageReading);
        }
        for (TeacherMessageReading value:teacherMessageReadings){
            s=teacherMessageReadingDao.addReader(value);
        }
        if (s!=null)
            returnInfo.setMsg(Msg1);
        else returnInfo.setMsg(Msg0);
        return returnInfo;
    }

    @Override
    public ReadInfo getTeacherMessageRead(int id) {
        ReadInfo readInfo = new ReadInfo();
        int read = 0, unread = 0;
        List<TeacherMessageReading> readings = teacherMessageReadingDao.findAllByMessage_id(id);
        List<Student> studentsRead = new ArrayList<>();
        List<Student> studentsUnread = new ArrayList<>();
        for (TeacherMessageReading teacherMessageReading : readings) {
            if (teacherMessageReading.is_read()) {
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
            messageInfo.setType(teacherMessage.getType());
            messageInfo.setReading(teacherMessageReadingDao.getTeacherMessageReadingsByMessage_id(teacherMessage.getId()));
            messageInfo.setUnread(teacherMessageReadingDao.getUnReadingsByMessage_id(teacherMessage.getId()));
            messageInfo.setTime(teacherMessage.getTime());
            messageInfos.add(messageInfo);
        }
        return messageInfos;
    }

    @Override
    public MessageInfo teacherGetTeacherMessageById(int id){
        TeacherMessage t=teacherMessageDao.getTeacherMessageById(id);
        if (t!=null)
        {
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setId(t.getId());
            messageInfo.setTitle(t.getTitle());
            messageInfo.setContent(t.getTeacherMessageContent().getContent());
            messageInfo.setTime(t.getTime());
            Teacher teacher = teacherDao.getTeacherById(t.getTeacher_id());
            messageInfo.setTeachername(teacher.getName());
            return messageInfo;
        }
        else return null;
    }

}
