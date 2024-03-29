package org.gdms.test.repository;

import org.gdms.test.entity.Deadline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

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
}
