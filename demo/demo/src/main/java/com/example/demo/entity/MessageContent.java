package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;

@Data
@Document(collection = "messageContent")
public class MessageContent {
    @Id
    @Column(name = "id")
    private int messageId;

    public void setMessageId(int messageId){
        this.messageId=messageId;
    }

    private String content;

    public String getContent() {
        return content;
    }
    public void setContent(String content){
        this.content=content;
    }
    public MessageContent(int messageId,String content){
        this.messageId=messageId;
        this.content=content;
    }

}