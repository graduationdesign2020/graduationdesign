package com.example.demo.dao;

import com.example.demo.entity.Message;
import com.example.demo.entity.Reading;

import java.util.List;

public interface MessageDao {
    Message getMessageById(int id);

    List<Message> getMessages();

    void sentMessage(Message message);//将(title,content,teacherId)插入到message表

    int haveRead(int messageId,String studentId);//将reading表中is_read改成1

    void addReader(Reading reading);//将(messageId,studentId,0)插入到reading表
}
