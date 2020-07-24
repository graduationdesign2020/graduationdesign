package com.example.demo.utils;

import com.example.demo.entity.Student;
import lombok.Data;

import java.util.List;

@Data
public class ReadInfo {
    private List<Student> studentsRead;
    private List<Student> studentsUnread;
    private int Read;
    private int unRead;
}
