package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "message",schema = "bysj")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "messageId")
public class Message {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private int messageId;

    public int getMessageId(){
        return messageId;
    }

    private String title;

    @Column(name = "teacher_id")
    private String teacherId;

    private String time;

    @Transient
    private String content;
    @Transient
    public void setContent(String content) {
        this.content = content;
    }
}
