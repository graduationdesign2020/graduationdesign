package org.gdms.test.controller;

import org.gdms.test.TestApplicationTests;
import org.gdms.test.entity.Grade;
import org.gdms.test.service.ProcessService;
import org.gdms.test.util.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessControllerTest extends TestApplicationTests {
    @Test
    public void contextLoads() {

    }

    private MockMvc mockMvc;
    private ObjectMapper om = new ObjectMapper();

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ProcessService processService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Transactional
    public void checkSelfProcess() throws Exception {
       MvcResult result = mockMvc.perform(post("/checkSelfProcess").content("{\"id\":\"305349154743\"}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        List<StateInfo> stateInfos = om.readValue(resultContent, new TypeReference<List<StateInfo> >() {});

        assertEquals(stateInfos.size(), processService.checkSelfProcess("305349154743").size());
    }

    @Test
    public void checkProcess() throws Exception {
        MvcResult result = mockMvc.perform(post("/checkProcess").content("{\"id\":\"03047a\"}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        System.out.println(resultContent);
    }

    @Test
    @Transactional
    public void checkSetDeadline() throws Exception {
        MvcResult result = mockMvc.perform(post("/setDeadline").content("{\"time\":\"2020-9-30 12:00:00\",\"id\":\"03047a\",\"state\":2}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ReturnInfo returnInfo = om.readValue(resultContent, new TypeReference<ReturnInfo>() {});

        assertEquals(returnInfo,processService.setDeadline("2020-9-30 12:00:00","03047a",2));
    }

    @Test
    @Transactional
    public void checkGetSelfGrade() throws Exception {
        MvcResult result = mockMvc.perform(post("/getSelfGrade").content("{\"id\":\"305349154743\"}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        Grade grade = om.readValue(resultContent, new TypeReference<Grade>() {});
        assertEquals(grade,processService.getGradeById("305349154743"));
    }

    @Test
    @Transactional
    public void checkGetGrades() throws Exception {
        MvcResult result = mockMvc.perform(post("/getGrades").content("{\"id\":\"03047a\"}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        List<GradeInfo> grade = om.readValue(resultContent, new TypeReference<List<GradeInfo>>() {});
        assertEquals(grade.size(),processService.getGradeByTeacher("03047a").size());
    }

    @Test
    @Transactional
    public void checkGetStudentProcess() throws Exception {
        MvcResult result = mockMvc.perform(post("/getStudentsProcess").content("\"1\"").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        List<StuProInfo> grade = om.readValue(resultContent, new TypeReference<List<StuProInfo>>() {});
        assertEquals(grade.size(),processService.getStudentsProcess("1").size());
    }

}
