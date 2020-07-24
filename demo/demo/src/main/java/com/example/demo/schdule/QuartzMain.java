package com.example.demo.schdule;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QuartzMain {
    public static void main(String args[]) throws Exception {
        JobDetail job= JobBuilder.newJob(SentJob.class).build();

        Trigger trigger= TriggerBuilder.newTrigger().withSchedule(SimpleScheduleBuilder.simpleSchedule()).build();

        Scheduler scheduler= StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(job,trigger);
    }
}
