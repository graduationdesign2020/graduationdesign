package org.graduationdesign.gdmsservercore.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.graduationdesign.gdmsservercore.service.ReplyService;
import org.graduationdesign.gdmsservercore.utils.Reply;
import org.graduationdesign.gdmsservercore.utils.ReplyInfo;
import org.graduationdesign.gdmsservercore.utils.ReplyMessageInfo;
import org.graduationdesign.gdmsservercore.utils.ReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ReturnInfo get(@RequestBody JSONObject params) {
        int id=Integer.parseInt(params.get("reading_id").toString());
        System.out.println(id);
        JSONArray replies= params.getJSONArray("reply");
        System.out.println(replies);
        List<Reply> keys= new ArrayList<>();
        for (Object reply : replies) {
            keys.add(JSONObject.parseObject(reply.toString(), Reply.class));
        }
        System.out.println(keys);
        return replyService.sentReply(id,keys);
    }
}



