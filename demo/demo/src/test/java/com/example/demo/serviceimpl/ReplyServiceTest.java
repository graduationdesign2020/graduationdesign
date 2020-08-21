package com.example.demo.serviceimpl;

import com.example.demo.DemoApplicationTests;
import com.example.demo.service.ReplyService;
import com.example.demo.utils.ReplyInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ReplyServiceTest extends DemoApplicationTests {
    @Autowired
    ReplyService replyService;

    @Test
    public void contextLoads() {
    }

}
