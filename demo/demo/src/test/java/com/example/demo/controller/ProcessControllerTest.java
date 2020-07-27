package com.example.demo.controller;

import com.example.demo.DemoApplicationTests;
import com.example.demo.utils.ProcessInfo;
import com.example.demo.utils.StateInfo;
import com.example.demo.service.ProcessService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessControllerTest extends DemoApplicationTests {
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
        MvcResult result = mockMvc.perform(post("/checkSelfProcess").content("{\"stu_id\": \"1\"}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        List<StateInfo> stateInfos = om.readValue(resultContent, new TypeReference<List<StateInfo> >() {});

        assertEquals(stateInfos, processService.checkSelfProcess("1"));
    }

//    @Test
//    public void checkProcess() throws Exception {
//        MvcResult result = mockMvc.perform(post("/checkProcess").content("{\"tea_id\": \"1\"}").contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk()).andReturn();
//        String resultContent = result.getResponse().getContentAsString();
//        List<ProcessInfo> processInfos = om.readValue(resultContent, new TypeReference<List<ProcessInfo>>() {} );
//
//        assertEquals(processInfos, processService.checkProcess("1"));
//    }

    @Test
    @Transactional
    public void checkSetDeadline() throws Exception {
        MvcResult result = mockMvc.perform(post("/setDeadline").content("{\"time\":\"2020-7-2316:00:00\",\"teacher\":\"1\",\"state\":2}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ReturnInfo returnInfo = om.readValue(resultContent, new TypeReference<ReturnInfo>() {});

        assertEquals(returnInfo,processService.setDeadline("2020-7-3 16:00:00","1",2));
    }

}
