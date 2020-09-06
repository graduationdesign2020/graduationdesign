package org.graduatiedesign.gdmsservercore.service;

import org.graduatiedesign.gdmsservercore.utils.Reply;
import org.graduatiedesign.gdmsservercore.utils.ReplyInfo;
import org.graduatiedesign.gdmsservercore.utils.ReplyMessageInfo;
import org.graduatiedesign.gdmsservercore.utils.ReturnInfo;

import java.util.List;

public interface ReplyService {
    ReturnInfo sentReply(int reading_id, List<Reply> replies);

    ReplyInfo getRepliesById(int id);

    ReplyMessageInfo getReplyMessage(int id,int reading_id);
}
