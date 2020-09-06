package org.gdms.test.serviceimpl;

import org.gdms.test.TestApplicationTests;
import org.gdms.test.entity.*;
import org.gdms.test.service.TeacherMessageService;
import org.gdms.test.util.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TeacherMessageServiceTest extends TestApplicationTests {

    @Autowired
    private TeacherMessageService teacherMessageService;

    @Test
    public void contextLoads() {
    }

    @Test
    @Transactional
    public void getTeacherMessageRead() {
        ReadInfo result = teacherMessageService.getTeacherMessageRead(10);
        ReadInfo compare = new ReadInfo();
        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        student1.init("305349154743", "学生", "软件工程", "电子信息与电气工程学院");
        student2.init("305359234696", "学生", "软件工程", "电子信息与电气工程学院");
        student3.init("305361034731", "学生", "软件工程", "电子信息与电气工程学院");
        List<Student> studentsRead = new ArrayList<>();
        List<Student> studentsUnread = new ArrayList<>();
        studentsRead.add(student3);
        studentsUnread.add(student1);
        studentsUnread.add(student2);
        compare.setStudentsRead(studentsRead);
        compare.setStudentsUnread(studentsUnread);
        compare.setRead(1);
        compare.setUnRead(2);
        assertEquals(compare, result);
    }

    @Test
    @Transactional
    public void checkGetTeacherMessageById() {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.init(10, 628, "no reply title 20200822", "饶若楠", "test content", "2020-08-22 23:15:14", false, 0, 0);
        MessageInfo compare=teacherMessageService.getTeacherMessageById(10,628);
        assertEquals(messageInfo,compare);
    }

    @Test
    @Transactional
    public void checkStudentGetTeacherMessages() {
        List<MessageInfo> messageInfos = teacherMessageService.getTeacherMessages("305361034731");
        assertEquals(9,messageInfos.size());
    }

    @Test
    @Transactional
    public void checkSentTeacherMessage(){
        ReturnInfo returnInfo=new ReturnInfo();
        returnInfo.setMsg("SUCCESS");
        List<String> strings=new ArrayList<>();
        strings.add("305349154743");
        strings.add("305359234696");
        assertEquals(returnInfo,teacherMessageService.sentTeacherMessage("no reply","03047a",strings,"new test content",null));
    }

    @Test
    @Transactional
    public void checkSentReplyMessage(){
        ReturnInfo returnInfo=new ReturnInfo();
        returnInfo.setMsg("SUCCESS");
        List<String> strings=new ArrayList<>();
        strings.add("305349154743");
        strings.add("305359234696");
        List<String> tasks=new ArrayList<>();
        tasks.add("身份证号");
        tasks.add("电话号码");
        assertEquals(returnInfo,teacherMessageService.sentTeacherMessage("no reply","03047a",strings,"new test content",tasks));
    }

    @Test
    public void checkGetStudentsByTeacher_id(){
        String teacher_id="03047a";
        List<Student> result=teacherMessageService.getStudentsByTeacher_id(teacher_id);
        assertEquals(result.size(),5);
    }
    @Test
    public void checkGetTeacherMessagesByTeacher_id(){
        String t_id="03047a";
        List<MessageInfo> compare=teacherMessageService.getTeacherMessagesByTeacher_id(t_id);
        assertEquals(compare.size(), 10);
    }
    @Test
    @Transactional
    public void teacherGetTeacherMessageById() {
        int id = 10;
        MessageInfo compare = teacherMessageService.teacherGetTeacherMessageById(id);
        MessageInfo messageInfo=new MessageInfo();
        messageInfo.init(id,0,"no reply title 20200822","饶若楠","test content","2020-08-22 23:15:14",false,0,0);
        assertEquals(compare, messageInfo);
    }


}
