package org.gdms.test.util;

import lombok.Data;

import java.util.List;

@Data
public class ReplyInfo {
    private List<StudentReply> studentsReply;

    private List<StudentReply> studentsUnreply;

    private int reply;

    private int unReply;

}
