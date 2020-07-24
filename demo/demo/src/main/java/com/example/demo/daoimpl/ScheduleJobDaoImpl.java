package com.example.demo.daoimpl;

import com.example.demo.dao.ScheduleJobDao;
import com.example.demo.entity.ScheduleJob;
import com.example.demo.repository.ScheduleJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class ScheduleJobDaoImpl implements ScheduleJobDao {
    @Autowired
    private ScheduleJobRepository scheduleJobRepository;

    @Override
    public List<ScheduleJob> getAll(){
        return scheduleJobRepository.getAll();
    }
    @Override
    public int updateJob(String student, int state, Timestamp time)
    {
        return scheduleJobRepository.updateMessage(time,student,state);
    }
    @Override
    public int addJob(String teacher,int state,Timestamp time)
    {
        ScheduleJob scheduleJob=new ScheduleJob();
        scheduleJob.setTeacher_id(teacher);
        scheduleJob.setState(state);
        scheduleJob.setTime(time);
        scheduleJobRepository.save(scheduleJob);
        return 1;
    }
}
