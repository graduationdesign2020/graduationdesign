package org.graduatiedesign.gdmsservercore.schdule;

import org.graduatiedesign.gdmsservercore.entity.Student;
import org.graduatiedesign.gdmsservercore.service.TeacherMessageService;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.sql.Timestamp;
import java.util.List;


public class SentJob implements Job {
    private TeacherMessageService teacherMessageService;

    public void execute(JobExecutionContext arg0) throws JobExecutionException{
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        JobDetail jobDetail =arg0.getJobDetail();
        int state=jobDetail.getJobDataMap().getInt("state");
        String teacher=jobDetail.getJobDataMap().getString("teacher");
        String time=jobDetail.getJobDataMap().getString("time");
        List<Student> list=teacherMessageService.getStudentsByTeacher_id(teacher);
//        for(Student student:list) {
//            String name=student.getName();
//            System.out.println(getMsg(name, state, time));
//        }
        System.out.println(getMsg(teacher, state, time));
    }
    public String getMsg(String student,int state,String time){
        String st=getState(state);
        Timestamp timestamp=Timestamp.valueOf(time);
        long time1=timestamp.getTime()+(long)1000*3600*24;
        Timestamp date =new Timestamp(time1);
        return student+"同学:\n 您的"+st+"将于24小时内截止提交，请于"+date+"前完成并提交!";
    }

    public String getState(int state){
        String st;
        switch (state){
            case 0:
                st="任务书";
                break;
            case 1:
                st="开题报告";
                break;
            case 2:
                st="中期检查";
                break;
            case 3:
                st="论文定稿";
                break;
            case 4:
                st="论文最终稿";
                break;
            default:
                st="WRONG";
                break;
        }
        return st;
    }
}
