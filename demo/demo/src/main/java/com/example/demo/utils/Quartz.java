package com.example.demo.utils;

import lombok.Data;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.sql.Date;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;


@Data
public class Quartz {
    private int id;

    private String name;

    private String time;

    private int state;

    public void start() {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // and start it off
            scheduler.start();

            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(HelloJob.class)
                    .withIdentity("job"+this.id, "group1")
                    .build();

            // Trigger the job to run now, and then repeat every 40 seconds
            SimpleTrigger trigger = (SimpleTrigger) newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startAt(Date.valueOf(this.time)) // some Date
                    .forJob("job"+this.id, "group1") // identify job with name, group strings
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);

            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
