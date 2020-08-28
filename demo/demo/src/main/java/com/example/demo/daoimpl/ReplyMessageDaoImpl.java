package com.example.demo.daoimpl;

import com.example.demo.dao.ReplyMessageDao;
import com.example.demo.entity.ReplyMessage;
import com.example.demo.repository.ReplyMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ReplyMessageDaoImpl implements ReplyMessageDao {
    @Autowired
    private ReplyMessageRepository replyMessageRepository;

    @Override
    public Optional<ReplyMessage> getById(int id){
        return replyMessageRepository.findById(id);
    }

    @Override
    public ReplyMessage saveReply(ReplyMessage replyMessage){
        return replyMessageRepository.save(replyMessage);
    }

}
