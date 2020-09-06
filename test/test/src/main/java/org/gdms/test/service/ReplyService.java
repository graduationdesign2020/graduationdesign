package org.gdms.test.service;

import org.gdms.test.util.*;

import javax.servlet.ServletOutputStream;
import java.util.List;

public interface ReplyService {
    ReturnInfo sentReply(int reading_id, List<Reply> replies);

    ReplyInfo getRepliesById(int id);

    ReplyMessageInfo getReplyMessage(int id,int reading_id);

//    void export(int id, ServletOutputStream out) throws Exception;
}
