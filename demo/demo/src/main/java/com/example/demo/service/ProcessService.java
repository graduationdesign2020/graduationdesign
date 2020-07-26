package com.example.demo.service;


import com.example.demo.entity.Grade;
import com.example.demo.utils.GradeInfo;
import com.example.demo.utils.ProcessInfo;
import com.example.demo.utils.StateInfo;
import com.example.demo.utils.ReturnInfo;

import java.util.List;

public interface ProcessService {
    List<StateInfo> checkSelfProcess(String stu_id);
    List<ProcessInfo> checkProcess(String tea_id);
    List<ProcessInfo> getStudentsProcess(String dept);
    ReturnInfo setDeadline(String end_time,String id,int state);
    Grade getGradeById(String id);
    List<GradeInfo> getGradeByTeacher(String id);
}
