package com.example.demo.utils;

import lombok.Data;

import java.util.List;

@Data
public class StuProInfo {
    private int state;
    private List<StuInfo> stuInfos;
}
