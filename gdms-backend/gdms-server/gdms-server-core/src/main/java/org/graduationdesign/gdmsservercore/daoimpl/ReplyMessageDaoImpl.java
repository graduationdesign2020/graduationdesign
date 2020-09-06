package org.graduationdesign.gdmsservercore.daoimpl;

import org.graduationdesign.gdmsservercore.dao.ReplyMessageDao;
import org.graduationdesign.gdmsservercore.entity.ReplyMessage;
import org.graduationdesign.gdmsservercore.repository.ReplyMessageRepository;
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
