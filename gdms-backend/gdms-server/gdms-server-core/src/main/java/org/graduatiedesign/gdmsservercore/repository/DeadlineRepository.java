package org.graduatiedesign.gdmsservercore.repository;

import org.graduatiedesign.gdmsservercore.entity.Deadline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

public interface DeadlineRepository extends JpaRepository<Deadline,Integer> {
    @Transactional
    @Modifying
    @Query(value = "update Deadline set end_time=:e_t where teacher_id=:id and state=:state")
    int changeDeadline(Timestamp e_t, String id, int state);

    @Query("select end_time from Deadline where teacher_id=:teacher_id and state=:state")
    Timestamp getByTeacher_idAndState(String teacher_id,int state);

    @Transactional
    @Modifying
    @Query(value="insert into schedulejob(teacher_id, state, end_time) values (?,?,?)",nativeQuery=true)
    int insertDeadLine(String id, int state,Timestamp e_t);

    @Query("from Deadline where job_status=0 and end_time=:end_time")
    List<Deadline> getDeadlinesByEnd_timeAndJob_status(Timestamp end_time);

    @Transactional
    @Modifying
    @Query(value = "update Deadline set job_status=1 where id=:id")
    int changeJobStatus(int id);
}
