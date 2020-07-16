package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "grade",schema = "GDMS")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Grade {
    @Id
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    private String teacher_grade;

    public String getTeacher_grade() {
        return teacher_grade;
    }

    public void setTeacher_grade(String teacher_grade) {
        this.teacher_grade = teacher_grade;
    }

    private String reply_grade;

    public String getReply_grade() {
        return reply_grade;
    }

    public void setReply_grade(String reply_grade) {
        this.reply_grade = reply_grade;
    }

    private String total_grade;

    public String getTotal_grade() {
        return total_grade;
    }

    public void setTotal_grade(String total_grade) {
        this.total_grade = total_grade;
    }
}
