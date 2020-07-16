package com.example.demo.daoimpl;

import com.example.demo.dao.StateDao;
import com.example.demo.entity.State;
import com.example.demo.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StateDaoImpl implements StateDao {
    @Autowired
    private StateRepository stateRepository;

    @Override
    public State getOneByProjAndState(String proj_id, Integer state) {
        return stateRepository.findByProject_idAndState(proj_id, state);
    }

    @Override
    public List<State> getStates(String proj_id) {
        return stateRepository.findAllByProject_id(proj_id);
    }
}
