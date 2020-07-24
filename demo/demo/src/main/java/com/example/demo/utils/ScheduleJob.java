package com.example.demo.utils;

import lombok.Data;

import javax.persistence.Id;
import java.sql.Timestamp;

@Data
public class ScheduleJob {
    @Id
    private int id;
    /**
     * 任务状态 是否启动任务
     */
    private String job_status;
    /**
     * 描述
     */
    private String teacher_id;

    private int state;

    private Timestamp time;
    /**
     * 任务是否有状态
     */
    private String is_concurrent;
}
