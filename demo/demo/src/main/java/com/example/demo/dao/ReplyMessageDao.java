package com.example.demo.dao;

import com.example.demo.entity.ReplyMessage;

import java.util.Optional;

public interface ReplyMessageDao {
    Optional<ReplyMessage> getById(int id);

    ReplyMessage saveReply(ReplyMessage replyMessage);
}
