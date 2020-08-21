package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.ReplyService;
import com.example.demo.utils.Reply;
import com.example.demo.utils.ReplyInfo;
import com.example.demo.utils.ReplyMessageInfo;
import com.example.demo.utils.ReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class ReplyController {
    @Autowired
    ReplyService replyService;

    @RequestMapping(path = "/getReplyMessage")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_TEACHER')")
    public ReplyMessageInfo getReplyMessage(@RequestBody Map<String,Integer> params) {
        Integer id=params.get("id");
        Integer reading_id=params.get("reading_id");
        return replyService.getReplyMessage(id,reading_id);
    }

    @RequestMapping(path = "/getTeacherMessageReply")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    public ReplyInfo getTeacherMessageReply(@RequestBody Map<String,Integer> params) {
        Integer id=params.get("id");
        return replyService.getRepliesById(id);
    }

    @RequestMapping(path = "/sendReply")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_TEACHER')")
    public ReturnInfo get(@RequestBody Map<String,Object> params) {
        int id=Integer.parseInt(params.get("reading_id").toString());
        List replies= JSONObject.parseObject(params.get("reply").toString(),List.class);
        List<Reply> keys= new ArrayList<>();
        for (Object reply : replies) {
            keys.add(JSONObject.parseObject(reply.toString(), Reply.class));
        }
        return replyService.sentReply(id,keys);
    }


}
