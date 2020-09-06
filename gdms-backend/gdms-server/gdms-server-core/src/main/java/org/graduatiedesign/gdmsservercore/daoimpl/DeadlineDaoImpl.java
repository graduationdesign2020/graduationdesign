package org.graduatiedesign.gdmsservercore.daoimpl;

import org.graduatiedesign.gdmsservercore.dao.DeadlineDao;
import org.graduatiedesign.gdmsservercore.repository.DeadlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public class DeadlineDaoImpl implements DeadlineDao {
    @Autowired
    private DeadlineRepository deadlineRepository;

    @Override
    public int addDeadline(String teacher_id, Timestamp timestamp, int state){
        return deadlineRepository.insertDeadLine(teacher_id,state,timestamp);
    }

    @Override
    public int changeDeadline(String teacher_id,Timestamp time,int state){
        return deadlineRepository.changeDeadline(time,teacher_id,state);
    }

    @Override
    public Timestamp getDeadline(String teacher_id,int state){
        return deadlineRepository.getByTeacher_idAndState(teacher_id, state);
    }
}
