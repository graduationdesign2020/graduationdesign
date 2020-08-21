package com.example.demo.service;

import com.example.demo.utils.Reply;
import com.example.demo.utils.ReplyInfo;
import com.example.demo.utils.ReplyMessageInfo;
import com.example.demo.utils.ReturnInfo;

import java.util.List;

public interface ReplyService {
    ReturnInfo sentReply(int reading_id, List<Reply> replies);

    ReplyInfo getRepliesById(int id);

    ReplyMessageInfo getReplyMessage(int id,int reading_id);
}
