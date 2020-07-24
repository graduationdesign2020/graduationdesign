package com.example.demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class ProcessInfo {
    private String name;
    private int finished;
    private int unfinished;
    private List<Student> finishedStu;
    private List<Student> unfinishedStu;
    private String end_time;
}
