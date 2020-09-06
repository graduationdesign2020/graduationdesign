package org.gdms.test.dao;

import org.gdms.test.entity.ReplyMessage;

import java.util.Optional;

public interface ReplyMessageDao {
    Optional<ReplyMessage> getById(int id);

    ReplyMessage saveReply(ReplyMessage replyMessage);
}
