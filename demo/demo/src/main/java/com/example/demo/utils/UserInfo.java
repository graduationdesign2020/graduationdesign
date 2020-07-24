package com.example.demo.utils;

import lombok.Data;

@Data
public class UserInfo {
    private String id;

    private String openid;

    private String name;

    private String dept;

    private String project;

    private String teacher;

    private String auth;

    public void init(String id,String openid,String name,String dept,String project,String teacher,String auth)
    {
        this.id=id;
        this.openid=openid;
        this.name=name;
        this.dept=dept;
        this.project=project;
        this.teacher=teacher;
        this.auth=auth;
    }
}
