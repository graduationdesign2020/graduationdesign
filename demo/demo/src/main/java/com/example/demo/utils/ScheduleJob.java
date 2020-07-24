package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@Table(name = "schedulejob",schema = "GDMS")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

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
