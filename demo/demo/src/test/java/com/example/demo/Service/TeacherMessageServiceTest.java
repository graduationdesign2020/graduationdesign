package com.example.demo.Service;

import com.example.demo.DemoApplicationTests;
import com.example.demo.dao.LoginDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.dao.TeacherMessageDao;
import com.example.demo.entity.*;
import com.example.demo.repository.TeacherMessageReadingRepository;
import com.example.demo.service.TeacherMessageService;
import com.example.demo.utils.MessageInfo;
import com.example.demo.utils.ReturnInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.constant.ReturnMsg.sendingMsg0;
import static com.example.demo.constant.ReturnMsg.sendingMsg1;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherMessageServiceTest extends DemoApplicationTests {

    @Autowired
    private TeacherMessageService teacherMessageService;
    @Autowired
    private TeacherMessageDao teacherMessageDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private LoginDao loginDao;
    @Autowired
    private TeacherMessageReadingRepository teacherMessageReadingRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    @Transactional
    public void getTeacherMessageRead() {
        ReadInfo result = teacherMessageService.getTeacherMessageRead(1);
        ReadInfo compare = new ReadInfo();
        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        student1.init("1", "111", "SE", "SE");
        student2.init("2", "222", "SE", "SE");
        student3.init("3", "333", "SE", "SE");
        List<Student> studentsRead = new ArrayList<>();
        List<Student> studentsUnread = new ArrayList<>();
        studentsRead.add(student1);
        studentsUnread.add(student2);
        studentsUnread.add(student3);
        compare.setStudentsRead(studentsRead);
        compare.setStudentsUnread(studentsUnread);
        compare.setRead(1);
        compare.setUnRead(2);

        assertEquals(compare, result);
    }

    @Test
    public void checkGetTeacherMessageById(){
        int id=1;
        int reading_id=1;
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
        MessageInfo compare=teacherMessageService.getTeacherMessageById(id,reading_id);
        assertEquals(compare, messageInfo);
    }

    @Test
    public void checkGetTeacherMessages(){
        String stu_id="1";
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
        List<MessageInfo> compare=teacherMessageService.getTeacherMessages(stu_id);
        assertEquals(compare, messageInfos);
    }

   @Test
    public void checkSentTeacherMessage(){
        String title="test title123456";
        String teacher_id="1";
        List<String> student_id=new ArrayList<>();
        student_id.add("1");
        student_id.add("3");
        String content="test content123456";
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
            s=teacherMessageDao.addReader(value);
        }
        if (s!=null)
            returnInfo.setMsg(sendingMsg1);
        else returnInfo.setMsg(sendingMsg0);
        ReturnInfo compare=teacherMessageService.sentTeacherMessage(title,teacher_id,student_id,content);
        assertEquals(returnInfo,compare);
    }

    @Test
    public void checkGetStudentsByTeacher_id(){
        String teacher_id="1";
        List<Student> result=teacherMessageService.getStudentsByTeacher_id(teacher_id);
        List<String> list=teacherMessageDao.getIdByTeacher_id(teacher_id);
        List<Student> students=new ArrayList<>();
        for (String s : list) {
            Student student = loginDao.getStudentById(s);
            students.add(student);
        }
        assertEquals(result,students);
    }

    @Test
    public void checkGetTeacherMessagesByTeacher_id(){
        String t_id="1";
        List<MessageInfo> compare=teacherMessageService.getTeacherMessagesByTeacher_id(t_id);
        List<TeacherMessage> list=teacherMessageDao.getTeacherMessagesByTeacher(t_id);
        List<MessageInfo> messageInfos=new ArrayList<>();
        for (TeacherMessage teacherMessage : list) {
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setTitle(teacherMessage.getTitle());
            messageInfo.setId(teacherMessage.getId());
            messageInfo.setReading(teacherMessageDao.getTeacherMessageReadingsByMessage_id(teacherMessage.getId()));
            messageInfo.setUnread(teacherMessageDao.getUnReadingsByMessage_id(teacherMessage.getId()));
            messageInfo.setTime(teacherMessage.getTime());
            messageInfos.add(messageInfo);
        }
        assertEquals(compare, messageInfos);
    }

    @Test
    public void teacherGetTeacherMessageById() {
        int id = 1;
        MessageInfo compare = teacherMessageService.teacherGetTeacherMessageById(id);
        TeacherMessage teacherMessage = teacherMessageDao.getTeacherMessageById(id);
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setId(teacherMessage.getId());
        messageInfo.setTitle(teacherMessage.getTitle());
        messageInfo.setContent(teacherMessage.getContent());
        messageInfo.setTime(teacherMessage.getTime());
        Teacher teacher = loginDao.getTeacherById(teacherMessage.getTeacher_id());
        messageInfo.setTeachername(teacher.getName());
        assertEquals(compare, messageInfo);
    }

}
