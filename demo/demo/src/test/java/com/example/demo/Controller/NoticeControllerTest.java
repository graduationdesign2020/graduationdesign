package com.example.demo.Controller;

import com.example.demo.DemoApplicationTests;
import com.example.demo.entity.DeptNotice;
import com.example.demo.entity.ReadInfo;
import com.example.demo.entity.SchoolNotice;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeControllerTest extends DemoApplicationTests {
    @Autowired
    private NoticeService noticeService;

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
    public void getSchoolNotices() throws Exception {
        MvcResult result = mockMvc.perform(post("/getSchoolNotices").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        List<SchoolNotice> schoolNotices = om.readValue(resultContent, new TypeReference<List<SchoolNotice>>() {});

        assertEquals(noticeService.getSchoolNotices(), schoolNotices);
    }

    @Test
    @Transactional
    public void getThreeSchoolNotices() throws Exception {
        MvcResult result = mockMvc.perform(post("/getThreeSchoolNotices").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        List<SchoolNotice> schoolNotices = om.readValue(resultContent, new TypeReference<List<SchoolNotice>>() {});

        assertEquals(noticeService.getThreeSchoolNotices(), schoolNotices);
    }

    @Test
    @Transactional
    public void getSchoolNotice() throws Exception {
        MvcResult result = mockMvc.perform(post("/getSchoolNotice").contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"id\":1}"))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        SchoolNotice schoolNotice = om.readValue(resultContent, new TypeReference<SchoolNotice>() {});

        assertEquals(noticeService.getSchoolNoticeById(1), schoolNotice);
    }

    @Test
    @Transactional
    public void getDeptNotices() throws Exception {
        MvcResult result = mockMvc.perform(post("/getDepartmentNotices").contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"dept\":\"SE\"}"))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        List<DeptNotice> deptNotices = om.readValue(resultContent, new TypeReference<List<DeptNotice> >() {});

        assertEquals(noticeService.getDeptNoticesByDept("SE"), deptNotices);
    }

    @Test
    @Transactional
    public void getThreeDeptNoticesByDepartment() throws Exception {
        MvcResult result = mockMvc.perform(post("/getThreeDepartmentNotices").contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"dept\":\"SE\"}"))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        List<DeptNotice> deptNotices = om.readValue(resultContent, new TypeReference<List<DeptNotice> >() {});

        assertEquals(noticeService.getThreeDeptNoticesByDepartment("SE"), deptNotices);
    }

    @Test
    @Transactional
    public void getDeptNotice() throws Exception {
        MvcResult result = mockMvc.perform(post("/getDepartmentNotice").contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"id\":1}"))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        DeptNotice deptNotice = om.readValue(resultContent, new TypeReference<DeptNotice>() {});

        assertEquals(noticeService.getDeptNoticeById(1), deptNotice);
    }
}
