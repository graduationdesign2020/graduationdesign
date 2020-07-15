package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "notice",schema = "bysj")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "noticeId")
public class Notice {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private int noticeId;

    public int getNoticeId(){
        return noticeId;
    }

    @Basic
    private String title;

    @Basic
    private boolean top;

    @Basic
    private String time;

    @Basic
    @Column(name = "read_number")
    private int reads;

    @Transient
    private String content;
    @Transient
    public void setContent(String content) {
        this.content = content;
    }
}
