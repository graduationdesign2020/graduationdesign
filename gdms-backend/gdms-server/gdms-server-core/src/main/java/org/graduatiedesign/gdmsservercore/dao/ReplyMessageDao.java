package org.graduatiedesign.gdmsservercore.dao;

import org.graduatiedesign.gdmsservercore.entity.ReplyMessage;

import java.util.Optional;

public interface ReplyMessageDao {
    Optional<ReplyMessage> getById(int id);

    ReplyMessage saveReply(ReplyMessage replyMessage);
}
