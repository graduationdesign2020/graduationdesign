package com.example.demo.repository;

import com.example.demo.entity.ScheduleJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

public interface ScheduleJobRepository extends JpaRepository<ScheduleJob,Integer> {
    @Query("from ScheduleJob ")
    List<ScheduleJob> getAll();

    @Query(value = "update ScheduleJob set time=:time where teacher_id=:student_id and state=:state")
    @Modifying
    @Transactional
    int updateMessage(Timestamp time, String student_id,int state);
}
