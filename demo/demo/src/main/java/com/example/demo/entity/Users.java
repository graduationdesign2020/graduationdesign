package com.example.demo.entity;

import com.example.demo.constant.Role;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "wechatusers",schema = "GDMS")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Users {
    @Id
    private String wechat_id;
    private String id;
    private String auth;

//    @Transient
//    private Role role;

//    public Users() {
//        switch (auth) {
//            case 0: this.role = Role.student;break;
//            case 1: this.role = Role.teacher;
//        }
//    }
}
