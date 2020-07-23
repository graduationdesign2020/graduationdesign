package com.example.demo.serviceimpl;

import com.example.demo.DemoApplicationTests;
import com.example.demo.dao.*;
import com.example.demo.entity.*;
import com.example.demo.repository.TeacherMessageReadingRepository;
import com.example.demo.service.TeacherMessageService;
import com.example.demo.utils.MessageInfo;
import com.example.demo.utils.ReturnInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void checkGetTeacherMessageById() {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.init(1, 1, "title1", "11111", "content1", "2020-07-17 14:19:48", false, 0, 0);
        MessageInfo compare=teacherMessageService.getTeacherMessageById(1,1);
        assertEquals(messageInfo,compare);
    }

    @Test
    public void checkGetTeacherMessages() {
        List<MessageInfo> messageInfos = teacherMessageService.getTeacherMessages("515015910016");
        assertEquals(0,messageInfos.size());
    }

    @Test
    @Transactional
    public void checkSentTeacherMessage(){
        ReturnInfo returnInfo=new ReturnInfo();
        returnInfo.setMsg("SUCCESS");
        List<String> strings=new ArrayList<>();
        strings.add("515015910016");
        strings.add("515020990002");
        assertEquals(returnInfo,teacherMessageService.sentTeacherMessage("new test title","1",strings,"new test content"));
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
        assertEquals(compare.size(), 10);
    }
    @Test
    @Transactional
    public void teacherGetTeacherMessageById() {
        int id = 1;
        MessageInfo compare = teacherMessageService.teacherGetTeacherMessageById(id);
        MessageInfo messageInfo=new MessageInfo();
        messageInfo.init(id,0,"title1","11111","content1","2020-07-17 14:19:48",false,0,0);
        assertEquals(compare, messageInfo);
    }


}