package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "schoolnotice",schema = "bysj")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class DeptNotice {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    @Basic
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Basic
    private String department;

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Basic
    private String time;
    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Transient
    private String content;
    @Transient
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
