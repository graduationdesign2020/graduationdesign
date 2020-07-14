package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;

@Data
@Document(collection = "noticeContent")
public class NoticeContent {
    @Id
    @Column(name = "id")
    private int noticeId;
    private String content;
    public String getContent() {
        return content;
    }
}
