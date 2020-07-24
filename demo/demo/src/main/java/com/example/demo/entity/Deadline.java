package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "deadline",schema = "GDMS")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Deadline {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    private String teacher_id;

    private Timestamp time;

    private int state;
}
