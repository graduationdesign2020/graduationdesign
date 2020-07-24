package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "deptnotice",schema = "GDMS")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class DeptNotice {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Basic
    private String title;

    @Basic
    private String department;

    @Basic
    private String time;

    @Transient
    private String content;
    @Transient
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void init(int id, String title, String department, String time) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.department = department;
    }
}
