package org.graduationdesign.gdmsservercore.dao;

import org.graduationdesign.gdmsservercore.entity.ReplyMessage;

import java.util.Optional;

public interface ReplyMessageDao {
    Optional<ReplyMessage> getById(int id);

    ReplyMessage saveReply(ReplyMessage replyMessage);
}
