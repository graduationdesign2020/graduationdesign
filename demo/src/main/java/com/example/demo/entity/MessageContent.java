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
    private String content;

    public String getContent() {
        return content;
    }
}