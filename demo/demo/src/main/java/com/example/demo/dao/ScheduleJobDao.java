package com.example.demo.dao;

import com.example.demo.entity.ScheduleJob;

import java.sql.Timestamp;
import java.util.List;

public interface ScheduleJobDao {
    List<ScheduleJob> getAll();
    int updateJob(String student, int state, Timestamp time);
    int addJob(String student,int state,Timestamp time);
}
