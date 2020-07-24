package com.example.demo.service;

import com.example.demo.entity.ProcessInfo;
import com.example.demo.entity.State;
import com.example.demo.entity.StateInfo;
import com.example.demo.utils.ReturnInfo;

import java.util.List;

public interface ProcessService {
    List<StateInfo> checkSelfProcess(String stu_id);
    List<ProcessInfo> checkProcess(String tea_id);
    ReturnInfo setDeadline(String end_time,String id,int state);
}
