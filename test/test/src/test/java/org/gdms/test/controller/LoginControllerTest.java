package org.gdms.test.controller;

import org.gdms.test.service.LoginService;
import org.gdms.test.util.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gdms.test.TestApplicationTests;
import org.junit.Assert;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest extends TestApplicationTests {
    @Autowired
    LoginService loginService;

    private MockMvc mockMvc;
    private ObjectMapper om = new ObjectMapper();

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void contextLoads() {

    }

    @Test
    @Transactional
    public void checkLogout() throws Exception {
        MvcResult result = mockMvc.perform(post("/mylogout").contentType(MediaType.APPLICATION_JSON_VALUE).content("{\"id\":\"816249335787\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.msg").value("SUCCESS")).andReturn();
    }

    @Test
    @Transactional
    public void checkRegisterWrong() throws Exception {
        MvcResult result = mockMvc.perform(post("/register").content("{\"openid\":\"1\",\"id\":\"518021910456\",\"name\":\"lixuan\",\"auth\":\"0\"}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ReturnInfo returnInfo = om.readValue(resultContent, new TypeReference<ReturnInfo>() { });
        ReturnInfo returnInfo1=new ReturnInfo();
        returnInfo1.setMsg("WRONG");
        assertEquals(returnInfo1, returnInfo);
    }

    @Test
    @Transactional
    public void checkRegisterStudentSuccess() throws Exception {
        MvcResult result = mockMvc.perform(post("/register").content("{\"openid\":\"1\",\"id\":\"111\",\"name\":\"1\",\"auth\":\"0\"}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ReturnInfo returnInfo = om.readValue(resultContent, new TypeReference<ReturnInfo>() { });
        ReturnInfo returnInfo1=new ReturnInfo();
        returnInfo1.setMsg("SUCCESS");
        UserInfo userInfo=new UserInfo();
        userInfo.setId("111");
        userInfo.setName("1");
        userInfo.setTeacher("aaa");
        userInfo.setDept("1");
        userInfo.setProject("1");
        returnInfo1.setUserData(userInfo);
        assertEquals(returnInfo1, returnInfo);
    }

    @Test
    @Transactional
    public void checkRegisterTeacherSuccess() throws Exception {
        MvcResult result = mockMvc.perform(post("/register").content("{\"openid\":\"1\",\"id\":\"abcdefg\",\"name\":\"aaa\",\"auth\":\"1\"}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ReturnInfo returnInfo = om.readValue(resultContent, new TypeReference<ReturnInfo>() { });
        ReturnInfo returnInfo1=new ReturnInfo();
        returnInfo1.setMsg("SUCCESS");
        UserInfo userInfo=new UserInfo();
        userInfo.setId("abcdefg");
        userInfo.setName("aaa");
        userInfo.setTeacher(null);
        userInfo.setDept("aaa");
        userInfo.setProject(null);
        returnInfo1.setUserData(userInfo);
        assertEquals(returnInfo1, returnInfo);
    }

    @Test
    @Transactional
    public void checkUserData() throws Exception{
        MvcResult result = mockMvc.perform(post("/getUserData").content("{\"id\":\"305349154743\",\"auth\":\"ROLE_STUDENT\"}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();
        UserInfo compare = om.readValue(resultContent, new TypeReference<UserInfo>() { });
        Assert.assertEquals(compare.getId(), "305349154743");
    }

}
