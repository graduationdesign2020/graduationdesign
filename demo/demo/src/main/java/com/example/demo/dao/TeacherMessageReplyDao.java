package com.example.demo.dao;

import com.example.demo.entity.ReplyMessage;
import com.example.demo.entity.TeacherMessageReply;
import com.example.demo.utils.Reply;

import java.util.List;
import java.util.Optional;

public interface TeacherMessageReplyDao {
    int setReply(List<Reply> replies,int id);//将is_read改成1

    TeacherMessageReply addReplier(TeacherMessageReply teacherMessageReply);

    Optional<ReplyMessage> getById(int id);

    int getRepliesByMessage_id(int message_id);

    int getUnRepliesByMessage_id(int message_id);

    List<TeacherMessageReply> getReplyMessagesByStudent_id(String stu_id);

    List<TeacherMessageReply> findAllByMessage_id(int id);
}
