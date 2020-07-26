package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;

@Data
@Document(collection = "teachermessagecontent")
public class TeacherMessageContent {
    @Id
    @Column(name = "_id")
    private int id;

    private String content;

    public TeacherMessageContent(int id, String content){
        this.id=id;
        this.content=content;
    }

}