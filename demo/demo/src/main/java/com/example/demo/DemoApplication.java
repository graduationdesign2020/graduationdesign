package com.example.demo;

import com.example.demo.dao.ScheduleJobDao;
import com.example.demo.entity.ScheduleJob;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SchedulerFactoryBean schedulerFactoryBean=new SchedulerFactoryBean();
		ScheduleJobDao scheduleJobDao;
		Scheduler scheduler= schedulerFactoryBean.getScheduler();
		List<ScheduleJob> jobList=scheduleJobDao.getAll();
		for (ScheduleJob job : jobList) {
			addJob(job);
		}
		SpringApplication.run(DemoApplication.class, args);
	}

}
