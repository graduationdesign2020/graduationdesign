package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import java.util.List;

@Data
@Document(collection = "teachermessagecontent")
public class TeacherMessageContent {
    @Id
    @Column(name = "_id")
    private int id;

    private String title;

    private List<String> students;

    private String content;


}