package com.example.demo.Controller;

import com.example.demo.DemoApplicationTests;
import com.example.demo.entity.ReadInfo;
import com.example.demo.service.NoticeService;
import com.example.demo.service.ProcessService;
import com.example.demo.service.TeacherMessageService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeControllerTest extends DemoApplicationTests {
    @Autowired
    private TeacherMessageService teacherMessageService;

    private MockMvc mockMvc;
    private ObjectMapper om = new ObjectMapper();

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void getTeacherMessageRead() throws Exception {
        MvcResult result = mockMvc.perform(post("/getTeacherMessageRead").content("101").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ReadInfo readInfo = om.readValue(resultContent, new TypeReference<ReadInfo>() { });

        assertEquals(teacherMessageService.getTeacherMessageRead("101"), readInfo);
    }
}
