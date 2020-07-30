package com.example.demo.utils;

import lombok.Data;

@Data
public class UserInfo {
    private String id;

    private String name;

    private String dept;

    private String project;

    private String teacher;


    public void init(String id,String name,String dept,String project,String teacher)
    {
        this.id=id;
        this.name=name;
        this.dept=dept;
        this.project=project;
        this.teacher=teacher;
    }
}
