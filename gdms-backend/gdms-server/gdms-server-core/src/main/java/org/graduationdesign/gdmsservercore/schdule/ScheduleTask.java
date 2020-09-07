package org.graduationdesign.gdmsservercore.schdule;

import org.graduationdesign.gdmsservercore.dao.ProjectDao;
import org.graduationdesign.gdmsservercore.dao.StudentDao;
import org.graduationdesign.gdmsservercore.entity.Deadline;
import org.graduationdesign.gdmsservercore.repository.DeadlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduleTask {
    @Autowired
    DeadlineRepository deadlineRepository;
    @Autowired
    ProjectDao projectDao;
    @Autowired
    StudentDao studentDao;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void sentJob(){
        Date date=new Date();
        Timestamp currentTime=new Timestamp(date.getTime());
        long time=currentTime.getTime()+(long)1000*3600*24;
        Timestamp nextTime=new Timestamp(time);
        List<Deadline> deadlines=deadlineRepository.getDeadlinesByEnd_timeAndJob_status(nextTime);
        if(deadlines.size()!=0){
            for(Deadline deadline:deadlines){
                List<String> students=projectDao.getIdByTeacher_id(deadline.getTeacher_id());
                for(String student:students){
                    String name=studentDao.getOne(student).getName();
                    String msg=getMsg(name,deadline.getState(),deadline.getEnd_time());
                    System.out.println(msg);
                }
                deadlineRepository.changeJobStatus(deadline.getId());
            }
        }
    }

    public String getMsg(String student,int state,Timestamp time){
        String st=getState(state);
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String ddl = simpleDateFormat.format(new Date(time.getTime()));
        return student+"同学:\n 您的"+st+"将于24小时内截止提交，请于"+ddl+"前完成并提交!";
    }

    public String getState(int state) {
        String st;
        switch (state) {
            case 0:
                st = "任务书";
                break;
            case 1:
                st = "开题报告";
                break;
            case 2:
                st = "中期检查";
                break;
            case 3:
                st = "论文定稿";
                break;
            case 4:
                st = "论文最终稿";
                break;
            default:
                st = "WRONG";
                break;
        }
        return st;
    }

    }
