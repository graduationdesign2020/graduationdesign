package org.gdms.test.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gdms.test.TestApplicationTests;
import org.gdms.test.service.ReplyService;
import org.gdms.test.util.*;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReplyControllerTest extends TestApplicationTests {
    @Test
    public void contextLoads() {

    }

    private MockMvc mockMvc;
    private ObjectMapper om = new ObjectMapper();

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ReplyService replyService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Transactional
    public void getReplyMessage() throws Exception {
        MvcResult result = mockMvc.perform(post("/getReplyMessage").content("{\"id\":13,\"reading_id\":633}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ReplyMessageInfo stateInfos = om.readValue(resultContent, new TypeReference<ReplyMessageInfo>() {});
        assertEquals(stateInfos.getReply().size(), replyService.getReplyMessage(13,633).getReply().size());
    }

    @Test
    @Transactional
    public void getReplyMessages() throws Exception {
        MvcResult result = mockMvc.perform(post("/getTeacherMessageReply").content("{\"id\":13,\"reading_id\":633}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ReplyInfo stateInfos = om.readValue(resultContent, new TypeReference<ReplyInfo>() {});
        assertEquals(stateInfos.getReply(), replyService.getRepliesById(13).getReply());
        assertEquals(stateInfos.getUnReply(), replyService.getRepliesById(13).getUnReply());
    }

    @Test
    @Transactional
    public void checkSentReply() throws Exception {
        MvcResult result = mockMvc.perform(post("/sendReply").content("{\"reading_id\":634,\"reply\":[{\"key\":\"身份证号\",\"value\":\"123456789123456789\"},{\"key\":\"电话号码\",\"value\":\"123455678912\"}]}").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        ReturnInfo stateInfos = om.readValue(resultContent, new TypeReference<ReturnInfo>() {});
        List<Reply> replies=new ArrayList<>();
        Reply reply1=new Reply();
        Reply reply2=new Reply();
        reply1.setKey("身份证号");
        reply1.setValue("123456789123456789");
        reply2.setValue("12345678912");
        reply2.setKey("电话号码");
        replies.add(reply1);
        replies.add(reply2);
        assertEquals(stateInfos.getMsg(), replyService.sentReply(634,replies).getMsg());
  }
}
