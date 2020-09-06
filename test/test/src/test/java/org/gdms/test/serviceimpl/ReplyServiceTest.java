package org.gdms.test.serviceimpl;


import org.gdms.test.TestApplicationTests;
import org.gdms.test.service.ReplyService;
import org.gdms.test.util.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ReplyServiceTest extends TestApplicationTests {

    @Autowired
    ReplyService replyService;

    @Test
    public void contextLoads() {
    }

    @Test
    @Transactional
    public void checkSentReplyFail(){
        List<Reply> replies=new ArrayList<>();
        Reply reply1=new Reply();
        Reply reply2=new Reply();
        reply1.setKey("身份证号");
        reply1.setValue("123456789123456789");
        reply2.setValue("12345678912");
        reply2.setKey("电话号码");
        replies.add(reply1);
        replies.add(reply2);
        ReturnInfo result=replyService.sentReply(10,replies);
        ReturnInfo compare=new ReturnInfo();
        compare.setMsg("FAIL");
        assertEquals(compare, result);
    }

    @Test
    @Transactional
    public void checkSentReply(){
        List<Reply> replies=new ArrayList<>();
        Reply reply1=new Reply();
        Reply reply2=new Reply();
        reply1.setKey("身份证号");
        reply1.setValue("123456789123456789");
        reply2.setValue("12345678912");
        reply2.setKey("电话号码");
        replies.add(reply1);
        replies.add(reply2);
        ReturnInfo result=replyService.sentReply(634,replies);
        ReturnInfo compare=new ReturnInfo();
        compare.setMsg("SUCCESS");
        assertEquals(compare, result);
    }

    @Test
    @Transactional
    public void checkGetRepliesById(){
        ReplyInfo compare=new ReplyInfo();
        ReplyInfo result=replyService.getRepliesById(13);
        assertEquals(4,result.getUnReply());
        assertEquals(1,result.getReply());
    }

    @Test
    @Transactional
    public void checkGetReplyMessage(){
        ReplyMessageInfo result=replyService.getReplyMessage(13,633);
        assertEquals(2,result.getReply().size());
    }

//    @Test
//    @Transactional
//    public void checkExport(){
//        replyService.export(13,);
//    }

}
