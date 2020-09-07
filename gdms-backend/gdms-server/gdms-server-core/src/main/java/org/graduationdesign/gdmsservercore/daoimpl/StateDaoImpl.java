package org.graduationdesign.gdmsservercore.daoimpl;

import org.graduationdesign.gdmsservercore.dao.StateDao;
import org.graduationdesign.gdmsservercore.entity.State;
import org.graduationdesign.gdmsservercore.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StateDaoImpl implements StateDao {
    @Autowired
    private StateRepository stateRepository;

    @Override
    public State getOneByProjAndState(String proj_id, Integer state) {
        return stateRepository.findByProjectAndState(proj_id, state);
    }

    @Override
    public List<State> getStates(String proj_id) {
        return stateRepository.findAllByProject(proj_id);
    }

}
