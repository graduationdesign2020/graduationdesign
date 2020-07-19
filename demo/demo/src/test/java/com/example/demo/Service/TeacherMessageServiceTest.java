package com.example.demo.Service;

import com.example.demo.DemoApplicationTests;
import com.example.demo.dao.StudentDao;
import com.example.demo.dao.TeacherMessageDao;
import com.example.demo.entity.ReadInfo;
import com.example.demo.entity.Student;
import com.example.demo.service.TeacherMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
}
