package org.gdms.test.controller;

import org.gdms.test.entity.Student;
import org.gdms.test.service.TeacherMessageService;
import org.gdms.test.util.*;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageControllerTest {
    @Autowired
    TeacherMessageService teacherMessageService;

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
    public void contextLoads() {
    }

    @Test
    @Transactional
    public void checkTeacherGetStudents() throws Exception {
        MvcResult result = mockMvc.perform(get("/teacherGetStudents").content("{\"id\":\"03047a\"}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        List<Student> students = om.readValue(resultContent, new TypeReference<List<Student>>() {});

        assertEquals(students.size(), 5);
    }

    @Test
    @Transactional
    public void getTeacherMessage() throws Exception {
        MvcResult result = mockMvc.perform(get("/getTeacherMessage").content("{\"id\":10,\"reading_id\":628}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        MessageInfo messageInfo = om.readValue(resultContent, new TypeReference<MessageInfo>() {});
        assertEquals(messageInfo.getContent(), teacherMessageService.getTeacherMessageById(10,628).getContent());
    }

    @Test
    @Transactional
    public void getTeacherMessages() throws Exception {
        MvcResult result = mockMvc.perform(get("/getTeacherMessages").content("{\"id\":\"305361034731\",\"auth\":0}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        List<MessageInfo> messageInfos = om.readValue(resultContent, new TypeReference<List<MessageInfo>>() {});

        assertEquals(messageInfos.size(), teacherMessageService.getTeacherMessages("305361034731").size());
    }

    @Test
    @Transactional
    public void getTeacherMessagesByTeacher() throws Exception {
        MvcResult result = mockMvc.perform(get("/getTeacherMessages").content("{\"id\":\"03047a\",\"auth\":1}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        List<MessageInfo> messageInfos = om.readValue(resultContent, new TypeReference<List<MessageInfo>>() {});
        assertEquals(messageInfos.size(), teacherMessageService.getTeacherMessagesByTeacher_id("03047a").size());
    }

    @Test
    @Transactional
    public void getTeacherMessageByTeacher() throws Exception {
        MvcResult result = mockMvc.perform(get("/teacherGetTeacherMessage").content("{\"id\":10}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        MessageInfo messageInfo = om.readValue(resultContent, new TypeReference<MessageInfo>() {});
        assertEquals(messageInfo.getContent(), teacherMessageService.teacherGetTeacherMessageById(10).getContent());
    }

    @Test
    @Transactional
    public void getTeacherMessageRead() throws Exception {
        MvcResult result = mockMvc.perform(post("/getTeacherMessageRead").content("{\"id\":10}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ReadInfo readInfo = om.readValue(resultContent, new TypeReference<ReadInfo>() { });

        assertEquals(teacherMessageService.getTeacherMessageRead(10).getRead(), readInfo.getRead());
        assertEquals(teacherMessageService.getTeacherMessageRead(10).getUnRead(), readInfo.getUnRead());

    }

    @Test
    @Transactional
    public void checkSentMessage() throws Exception {
        MvcResult result = mockMvc.perform(post("/sendMessages").content("{\"title\":\"no reply title 20200822\",\"id\":\"03047a\",\"content\":\"test content\",\"students\":[\"305349154743\",\"305359234696\"],\"tasks\":[\"id\",\"number\"]}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ReturnInfo returnInfo = om.readValue(resultContent, new TypeReference<ReturnInfo>() {});
        List<String> students=new ArrayList<>();
        students.add("305349154743");
        students.add("305359234696");
        List<String> tasks=new ArrayList<>();
        tasks.add("number");
        tasks.add("id");
        assertEquals(returnInfo, teacherMessageService.sentTeacherMessage("no reply title 20200822","03047a", students ,"test content",tasks));
    }
}
