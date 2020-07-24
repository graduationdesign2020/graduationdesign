package com.example.demo.daoimpl;

import com.example.demo.dao.DeadlineDao;
import com.example.demo.entity.Deadline;
import com.example.demo.repository.DeadlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public class DeadlineDaoImpl implements DeadlineDao {
    @Autowired
    private DeadlineRepository deadlineRepository;

    @Override
    public Deadline addDeadline(String teacher_id, Timestamp timestamp, int state){
        Deadline deadline=new Deadline();
        deadline.setState(state);
        deadline.setTeacher_id(teacher_id);
        deadline.setTime(timestamp);
        return deadlineRepository.save(deadline);
    }

    @Override
    public int changeDeadline(String teacher_id,Timestamp time,int state){
        return deadlineRepository.changeDeadline(time,teacher_id,state);
    }
}