package com.example.demo.schdule;

import com.example.demo.utils.ScheduleJob;

import java.sql.Timestamp;
import java.util.logging.Logger;

public class LoadTask {
    private static final Logger log=Logger.getLogger("");
    public static boolean timeTask(Timestamp time,int msgId,String teacher_id,int state){
        ScheduleJob job=new ScheduleJob();
        String jobName=msgId+"_job";
        job.setId(msgId);
        job.setTeacher_id(teacher_id);
        job.setState(state);
        job.setTime(time);
        job.setJob_status("0");
        try {
            QuartzManager.removeJob(jobName);
            QuartzManager.addJob(jobName,time,job);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
