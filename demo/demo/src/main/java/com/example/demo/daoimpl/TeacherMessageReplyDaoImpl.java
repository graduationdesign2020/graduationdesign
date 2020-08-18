package com.example.demo.daoimpl;

import com.example.demo.dao.TeacherMessageReplyDao;
import com.example.demo.entity.ReplyMessage;
import com.example.demo.entity.TeacherMessageReply;
import com.example.demo.repository.ReplyMessageRepository;
import com.example.demo.repository.TeacherMessageReplyRepository;
import com.example.demo.utils.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TeacherMessageReplyDaoImpl implements TeacherMessageReplyDao {
    @Autowired
    private ReplyMessageRepository replyMessageRepository;
    @Autowired
    private TeacherMessageReplyRepository teacherMessageReplyRepository;

    @Override
    public int setReply(List<Reply> replies, int id){
        ReplyMessage replyMessage=new ReplyMessage();
        replyMessage.setId(id);
        replyMessage.setReply(replies);
        replyMessageRepository.save(replyMessage);
        return teacherMessageReplyRepository.setReply(id);
    }

    @Override
    public TeacherMessageReply addReplier(TeacherMessageReply teacherMessageReply){
        return teacherMessageReplyRepository.save(teacherMessageReply);
    }

    @Override
    public int getRepliesByMessage_id(int message_id){
        return teacherMessageReplyRepository.getRepliesByMessage_id(message_id);
    }

    @Override
    public int getUnRepliesByMessage_id(int message_id){
        return teacherMessageReplyRepository.getUnRepliesByMessage_id(message_id);
    }

    @Override
    public List<TeacherMessageReply> getReplyMessagesByStudent_id(String stu_id){
        return teacherMessageReplyRepository.getReplyMessagesByStudent_id(stu_id);
    }

    @Override
    public List<TeacherMessageReply> findAllByMessage_id(int id){
        List<TeacherMessageReply> teacherMessageReplies=teacherMessageReplyRepository.findAllByMessage_id(id);
        for (TeacherMessageReply teacherMessageReply : teacherMessageReplies) {
            Optional<ReplyMessage> replyMessage = replyMessageRepository.findById(teacherMessageReply.getId());
            if (replyMessage.isPresent()) {
                ReplyMessage r = replyMessage.get();
                teacherMessageReply.setReply(r);
            }
        }
        return teacherMessageReplies;
    }
}
