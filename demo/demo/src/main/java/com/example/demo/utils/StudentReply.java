package com.example.demo.utils;

import lombok.Data;

import java.util.List;

@Data
public class StudentReply {
    private String name;

    private String id;

    private List<Reply> reply;
}
