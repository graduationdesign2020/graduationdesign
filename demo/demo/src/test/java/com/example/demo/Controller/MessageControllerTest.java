package com.example.demo.Controller;

import com.example.demo.entity.Student;
import com.example.demo.entity.TeacherMessage;
import com.example.demo.service.LoginService;
import com.example.demo.service.TeacherMessageService;
import com.example.demo.utils.MessageInfo;
import com.example.demo.utils.ReturnInfo;
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
        MvcResult result = mockMvc.perform(get("/teacherGetStudents").content("{\"id\":\"1\"}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        List<Student> students = om.readValue(resultContent, new TypeReference<List<Student>>() {});

        assertEquals(students, teacherMessageService.getStudentsByTeacher_id("1"));
    }

    @Test
    @Transactional
    public void getTeacherMessage() throws Exception {
        MvcResult result = mockMvc.perform(get("/getTeacherMessage").content("{\"id\":1,\"reading_id\":1}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        MessageInfo messageInfo = om.readValue(resultContent, new TypeReference<MessageInfo>() {});
        assertEquals(messageInfo, teacherMessageService.getTeacherMessageById(1,1));
    }

    @Test
    @Transactional
    public void getTeacherMessages() throws Exception {
        MvcResult result = mockMvc.perform(get("/teacherGetStudents").content("{\"student_id\":\"1\"}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        List<MessageInfo> messageInfos = om.readValue(resultContent, new TypeReference<List<MessageInfo>>() {});

        assertEquals(messageInfos, teacherMessageService.getTeacherMessages("1"));
    }

    @Test
    @Transactional
    public void getTeacherMessagesByTeacher() throws Exception {
        MvcResult result = mockMvc.perform(get("/teacherGetTeacherMessages").content("{\"teacher_id\":\"1\"}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        List<MessageInfo> messageInfos = om.readValue(resultContent, new TypeReference<List<MessageInfo>>() {});
        assertEquals(messageInfos, teacherMessageService.getTeacherMessagesByTeacher_id("1"));
    }

    @Test
    @Transactional
    public void getTeacherMessageByTeacher() throws Exception {
        MvcResult result = mockMvc.perform(get("/teacherGetTeacherMessage").content("{\"id\":1}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        MessageInfo messageInfo = om.readValue(resultContent, new TypeReference<MessageInfo>() {});
        assertEquals(messageInfo, teacherMessageService.teacherGetTeacherMessageById(1));
    }

    @Test
    @Transactional
    public void sentMessage() throws Exception {
        MvcResult result = mockMvc.perform(get("/teacherGetTeacherMessage").content("{\"title\":\"1\",\"teacher_id\":\"1\",\"student_id\":[\"1\",\"3\"],\"content\":\"content2020\"}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ReturnInfo returnInfo = om.readValue(resultContent, new TypeReference<ReturnInfo>() {});
        List<String> students=new ArrayList<>();
        students.add("1");
        students.add("3");
        assertEquals(returnInfo, teacherMessageService.sentTeacherMessage("title2020","1", students ,"content2020"));
    }
}
