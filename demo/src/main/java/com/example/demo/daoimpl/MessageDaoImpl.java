package com.example.demo.daoimpl;

import com.example.demo.dao.MessageDao;
import com.example.demo.entity.Message;
import com.example.demo.entity.MessageContent;
import com.example.demo.entity.NoticeContent;
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
        List<Message> messages=messageRepository.getMessages();
        for(int i=0;i<messages.size();i++)
        {
            int index= messages.get(i).getMessageId();
            MessageContent messageContent=messageContentRepository.findByMessageId(index);
            messages.get(i).setContent(messageContent.getContent());
        }
        return messages;
    }

    @Override
    public int sentMessage(String title,String content,String teacherId){
        return messageRepository.sentMessage(title, teacherId);
    }

    @Override
    public int haveRead(int messageId,String studentId){
        return readingRepository.haveRead(messageId, studentId);
    }

    @Override
    public int addReader(int messageId,String studentId){
        return readingRepository.addReader(messageId, studentId);
    }

}
