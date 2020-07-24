package com.example.demo.schdule;

import com.example.demo.utils.ScheduleJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.sql.Timestamp;


public class QuartzManager {
    private static SchedulerFactory schedulerFactory=new StdSchedulerFactory();
    private static String JOB_GROUP_NAME="MY_JOB_GROUP";
    private static String TRIGGER_GROUP_NAME="MY_TRIGGER_GROUP";

    /**
     * @Description add a job
     * @param jobName
     * @param time
     * @param schedulejob
     */
    public static void addJob(String jobName, Timestamp time, ScheduleJob schedulejob){
        try {
            Scheduler scheduler=schedulerFactory.getScheduler();
            JobDetail jobDetail=JobBuilder.newJob(SentJob.class).withIdentity(jobName,JOB_GROUP_NAME).usingJobData("time",schedulejob.getTime().toString()).usingJobData("teacher_id",schedulejob.getTeacher_id()).usingJobData("state",schedulejob.getState()).build();
            jobDetail.getJobDataMap().put("scheduleJob",schedulejob);
            Trigger trigger=TriggerBuilder.newTrigger().withIdentity(jobName,TRIGGER_GROUP_NAME).startAt(time).build();
            scheduler.scheduleJob(jobDetail,trigger);
            if(!scheduler.isStarted()){
                scheduler.start();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description remove a job
     * @param jobName
     */

    public static void removeJob(String jobName){
        TriggerKey triggerKey=TriggerKey.triggerKey(jobName,TRIGGER_GROUP_NAME);
        JobKey jobKey=JobKey.jobKey(jobName,JOB_GROUP_NAME);
        try {
            Scheduler scheduler=schedulerFactory.getScheduler();
            Trigger trigger=scheduler.getTrigger(triggerKey);
            if(trigger==null){
                return;
            }
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description start all jobs
     */
    public static void startJobs(){
        try {
            Scheduler scheduler=schedulerFactory.getScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description end all jobs
     */

    public static void shutdownJobs(){
        try {
            Scheduler scheduler=schedulerFactory.getScheduler();
            if(!scheduler.isShutdown()){
                scheduler.shutdown();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
