package com.example.demo.repository;

import com.example.demo.entity.Deadline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

public interface DeadlineRepository extends JpaRepository<Deadline,Integer> {
    @Transactional
    @Modifying
    @Query(value = "update Deadline set time=:e_t where teacher_id=:id and state=:state")
    int changeDeadline(Timestamp e_t, String id, int state);
}
