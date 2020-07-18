package com.example.demo.repository;

import com.example.demo.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Integer> {
    State findByProject_idAndState(String proj_id, Integer state);
    List<State> findAllByProject_id(String proj_id);
}
