package com.example.demo.service;

import com.example.demo.entity.ProcessInfo;
import com.example.demo.entity.State;
import com.example.demo.entity.StateInfo;

import java.util.List;

public interface ProcessService {
    StateInfo checkSelfProcess(String stu_id);
    List<ProcessInfo> checkProcess(String tea_id);
}
