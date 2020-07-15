package com.example.demo.daoimpl;

import com.example.demo.dao.MessageDao;
import com.example.demo.entity.Message;
import com.example.demo.entity.MessageContent;
import com.example.demo.entity.NoticeContent;
import com.example.demo.entity.Reading;
import com.example.demo.repository.MessageContentRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDaoImpl implements MessageDao {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ReadingRepository readingRepository;
    @Autowired
    MessageContentRepository messageContentRepository;

    @Override
    public Message getMessageById(int id){
        Message message=messageRepository.getOne(id);
        MessageContent messageContent=messageContentRepository.findByMessageId(id);
        message.setContent(messageContent.getContent());
        return message;
    }

    @Override
    public List<Message> getMessages(){
        return messageRepository.getMessages();
    }

    @Override
    public void sentMessage(Message message){
        messageRepository.save(message);
        MessageContent messageContent=new MessageContent(message.getMessageId(),message.getContent());
        messageContentRepository.save(messageContent);
    }

    @Override
    public int haveRead(int messageId,String studentId){
        return readingRepository.haveRead(messageId, studentId);
    }

    @Override
    public void addReader(Reading reading){
        readingRepository.save(reading);
    }

}
