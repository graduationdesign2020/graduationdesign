package com.example.demo.serviceimpl;

import com.example.demo.DemoApplicationTests;
import com.example.demo.entity.*;
import com.example.demo.service.TeacherMessageService;
import com.example.demo.utils.MessageInfo;
import com.example.demo.utils.ReadInfo;
import com.example.demo.utils.ReturnInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TeacherMessageServiceTest extends DemoApplicationTests {

    @Autowired
    private TeacherMessageService teacherMessageService;

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
        student1.init("305349154743", "学生", "软件工程", "电子信息与电气工程学院");
        student2.init("305359234696", "学生", "软件工程", "电子信息与电气工程学院");
        student3.init("305361034731", "学生", "软件工程", "电子信息与电气工程学院");
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
    @Transactional
    public void checkGetTeacherMessageById() {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.init(1, 1, "test title", "饶若楠", "test content", "2020-07-31 09:44:21", false, 0, 0);
        MessageInfo compare=teacherMessageService.getTeacherMessageById(1,1);
        assertEquals(messageInfo,compare);
    }

    @Test
    @Transactional
    public void checkGetTeacherMessages() {
        List<MessageInfo> messageInfos = teacherMessageService.getTeacherMessages("305349154743");
        assertEquals(202,messageInfos.size());
    }

    @Test
    @Transactional
    public void checkSentTeacherMessage(){
        ReturnInfo returnInfo=new ReturnInfo();
        returnInfo.setMsg("SUCCESS");
        List<String> strings=new ArrayList<>();
        strings.add("305349154743");
        strings.add("305359234696");
        List<String> tasks=new ArrayList<>();
        tasks.add("身份证号");
        tasks.add("电话号码");
        assertEquals(returnInfo,teacherMessageService.sentTeacherMessage("no reply","03047a",strings,"new test content",null));
    }

    @Test
    public void checkGetStudentsByTeacher_id(){
        String teacher_id="1";
        List<Student> result=teacherMessageService.getStudentsByTeacher_id(teacher_id);
        assertEquals(result.size(),0);
    }
    @Test
    public void checkGetTeacherMessagesByTeacher_id(){
        String t_id="1";
        List<MessageInfo> compare=teacherMessageService.getTeacherMessagesByTeacher_id(t_id);
        assertEquals(compare.size(), 0);
    }
    @Test
    @Transactional
    public void teacherGetTeacherMessageById() {
        int id = 1;
        MessageInfo compare = teacherMessageService.teacherGetTeacherMessageById(id);
        MessageInfo messageInfo=new MessageInfo();
        messageInfo.init(id,0,"test title","饶若楠","test content","2020-07-31 09:44:21",false,0,0);
        assertEquals(compare, messageInfo);
    }


}
