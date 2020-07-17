package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "teachermessagereading",schema = "GDMS")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class TeacherMessageReading {
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
    private int message_id;

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

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
}
