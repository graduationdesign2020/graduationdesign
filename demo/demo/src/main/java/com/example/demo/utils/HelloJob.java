package com.example.demo.utils;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {
    public HelloJob (){
    }

    public void execute(JobExecutionContext context)
            throws JobExecutionException
    {
        int state=context.getJobDetail().getJobDataMap().getInt("state");
        String date=context.getJobDetail().getJobDataMap().getString("date");
        String name=context.getJobDetail().getJobDataMap().getString("name");
        String msg=getMsg(state,date,name);
        System.err.println("Hello!  HelloJob is executing.");
    }

    public String getMsg(int state,String date,String name){
        getMsg getMsg=new getMsg();
        String statename=getMsg.getState(state);
        String msg="提交提醒:"+name+"同学，您的"+statename+"将于24小时内截止提交，请于"+date+"前提交！";
        return msg;
    }


}
