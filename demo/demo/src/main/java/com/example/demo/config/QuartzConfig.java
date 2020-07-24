package com.example.demo.config;

import com.example.demo.schdule.SentJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean(){
        JobDetailFactoryBean factoryBean=new JobDetailFactoryBean();
        factoryBean.setJobClass(SentJob.class);
        return factoryBean;
    }

    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean){
        SimpleTriggerFactoryBean factoryBean=new SimpleTriggerFactoryBean();
        //关联jobDetail
        factoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        factoryBean.setRepeatCount(1);
        return factoryBean;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(SimpleTriggerFactoryBean simpleTriggerFactoryBean){
        SchedulerFactoryBean factoryBean=new SchedulerFactoryBean();
        factoryBean.setTriggers(simpleTriggerFactoryBean.getObject());
        return factoryBean;
    }
}
