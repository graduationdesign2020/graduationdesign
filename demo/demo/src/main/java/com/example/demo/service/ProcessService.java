package com.example.demo.service;

import com.example.demo.utils.ProcessInfo;
import com.example.demo.utils.StateInfo;
import com.example.demo.utils.ReturnInfo;

import java.util.List;

public interface ProcessService {
    List<StateInfo> checkSelfProcess(String stu_id);
    List<ProcessInfo> checkProcess(String tea_id);
    ReturnInfo setDeadline(String end_time,List<String> id,int state);
    List<ProcessInfo> getStudentsProcess(String dept);
}
