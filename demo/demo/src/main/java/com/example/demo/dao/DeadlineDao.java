package com.example.demo.dao;

import com.example.demo.entity.Deadline;

import java.sql.Timestamp;

public interface DeadlineDao {
    int addDeadline(String teacher_id, Timestamp timestamp, int state);

    int changeDeadline(String teacher_id,Timestamp time,int state);

    Timestamp getDeadline(String teacher_id,int state);
}
