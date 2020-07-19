package com.example.demo.dao;

import com.example.demo.entity.State;

import java.util.List;

public interface StateDao {
    State getOneByProjAndState(String proj_id, Integer state);

    List<State> getStates(String proj_id);
}
