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

    public void setMessageId(int messageId){
        this.messageId=messageId;
    }
    public int getMessageId(){
        return messageId;
    }

    @Basic
    private String title;

    public void setTitle(String title){
        this.title=title;
    }
    public String getTitle(){
        return title;
    }

    @Basic
    @Column(name = "teacher_id")
    private String teacherId;

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    @Basic
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Transient
    private String content;
    @Transient
    public void setContent(String content) {
        this.content = content;
    }
    public String getContent(){return content;}
}
