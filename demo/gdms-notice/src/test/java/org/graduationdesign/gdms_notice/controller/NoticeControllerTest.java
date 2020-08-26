package org.graduationdesign.gdms_notice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.graduationdesign.gdms_notice.GdmsNoticeApplicationTests;
import org.graduationdesign.gdms_notice.entity.DeptNotice;
import org.graduationdesign.gdms_notice.entity.SchoolNotice;
import org.graduationdesign.gdms_notice.service.NoticeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Rollback
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class NoticeControllerTest extends GdmsNoticeApplicationTests {
    @Autowired
    NoticeService noticeService;

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
    public void checkGetSchoolNotices() throws Exception {
        MvcResult result = mockMvc.perform(get("/getSchoolNotices"))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        List<SchoolNotice> schoolNotices = om.readValue(resultContent, new TypeReference<List<SchoolNotice>>() {});
        assertEquals(4, schoolNotices.size());
    }

    @Test
    @Transactional
    public void checkGetThreeSchoolNotices() throws Exception {
        MvcResult result = mockMvc.perform(get("/getThreeSchoolNotices"))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        List<SchoolNotice> schoolNotices = om.readValue(resultContent, new TypeReference<List<SchoolNotice>>() {});
        assertEquals(3, schoolNotices.size());
    }

    @Test
    @Transactional
    public void checkGetSchoolNotice() throws Exception {
        MvcResult result = mockMvc.perform(get("/getSchoolNotice").content("{\"id\":1}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        SchoolNotice schoolNotice = om.readValue(resultContent, new TypeReference<SchoolNotice>() {});
        assertEquals(schoolNotice.getId(), noticeService.getSchoolNoticeById(1).getId());
    }

//    @Test
//    @Transactional
//    public void checkGetDeptNotices() throws Exception {
//        MvcResult result = mockMvc.perform(post("/getDepartmentNotices").header("Authorization","Bearer eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJpc3MiOiJTbmFpbENsaW1iIiwiaWF0IjoxNTk4MzYzMTE3LCJzdWIiOiI4MTk3NDkzMzYwNzciLCJleHAiOjE1OTg5Njc5MTd9.zwtqdc0hoAqnzKtXWpw24veNKqy4gfnvUzBf2HElFV4"))
//                .andExpect(status().isOk()).andReturn();
//        String resultContent = result.getResponse().getContentAsString();
//        List<DeptNotice> deptNotices = om.readValue(resultContent, new TypeReference<List<DeptNotice>>() {});
//        assertEquals(4, deptNotices.size());
//    }

}
