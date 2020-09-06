package org.graduationdesign.gdmsservercore.service;

import org.graduationdesign.gdmsservercore.utils.Reply;
import org.graduationdesign.gdmsservercore.utils.ReplyInfo;
import org.graduationdesign.gdmsservercore.utils.ReplyMessageInfo;
import org.graduationdesign.gdmsservercore.utils.ReturnInfo;

import java.util.List;

public interface ReplyService {
    ReturnInfo sentReply(int reading_id, List<Reply> replies);

    ReplyInfo getRepliesById(int id);

    ReplyMessageInfo getReplyMessage(int id, int reading_id);
}
