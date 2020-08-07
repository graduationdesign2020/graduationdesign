package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "teachermessage",schema = "GDMS")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class TeacherMessage {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Basic
    private String title;

    @Basic
    private String teacher_id;

    @Basic
    private String time;

    @Transient
    private String content;

    @Transient
    private List<String> students;

}
