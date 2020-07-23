package com.example.demo.repository;

import com.example.demo.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Integer> {
    @Query("from State where project_id=:proj_id and state=:state")
    State findByProjectAndState(String proj_id, Integer state);

    @Query("from State where project_id=:proj_id")
    List<State> findAllByProject(String proj_id);


    @Transactional
    @Modifying
    @Query(value = "update State set end_time=:e_t where project_id=:id and state=:state")
    int setDeadline(String e_t,String id,int state);
}
