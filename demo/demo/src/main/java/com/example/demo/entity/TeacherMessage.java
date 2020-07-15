package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "teachermessage",schema = "bysj")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class TeacherMessage {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }

    @Basic
    private String title;

    public void setTitle(String title){
        this.title=title;
    }
    public String getTitle(){return title;}

    @Basic
    private String teacher_id;

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }
    public String getTeacher_id(){ return teacher_id;}

    @Basic
    private String student_id;

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
    public String getStudent_id(){return student_id;}

    @Basic
    private boolean is_read;

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }

    public boolean getIs_read() {
        return is_read;
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

    public TeacherMessage(String title,String teacher_id,String student_id,String time,String content){
        this.title=title;
        this.teacher_id=teacher_id;
        this.student_id=student_id;
        this.is_read=false;
        this.time=time;
        this.content=content;
    }
}
