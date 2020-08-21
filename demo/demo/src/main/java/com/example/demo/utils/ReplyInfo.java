package com.example.demo.utils;

import lombok.Data;

import java.util.List;

@Data
public class ReplyInfo {
    private List<StudentReply> studentsReply;

    private List<StudentUnreply> studentsUnreply;

    private int reply;

    private int unReply;

}
