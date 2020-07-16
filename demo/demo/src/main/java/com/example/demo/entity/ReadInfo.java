package com.example.demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class ReadInfo {
    private List<Student> studentsRead;
    private List<Student> studentsUnread;
    private int Read;
    private int unRead;
}
