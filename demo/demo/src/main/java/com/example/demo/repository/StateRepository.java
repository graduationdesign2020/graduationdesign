package com.example.demo.repository;

import com.example.demo.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Integer> {
    @Query("from State where project_id=:proj_id and state=:state")
    State findByProjectAndState(String proj_id, Integer state);

    @Query("from State where project_id=:proj_id")
    List<State> findAllByProject(String proj_id);
}
