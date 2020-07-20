package com.example.demo.Dao;

import com.example.demo.DemoApplicationTests;
import com.example.demo.dao.StudentDao;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentDaoTest extends DemoApplicationTests {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void contextLoads() {

    }

    @Test
    public void findByName() {
        Student result = studentRepository.findDistinctByName("夏目贵志");
        Student compare = studentDao.getByName("夏目贵志");
        System.out.println(result);
        assertEquals(result, compare);
    }
}
