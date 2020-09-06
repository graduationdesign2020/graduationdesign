package org.graduatiedesign.gdmsservercore.dao;

import org.graduatiedesign.gdmsservercore.entity.State;

import java.util.List;

public interface StateDao {
    State getOneByProjAndState(String proj_id, Integer state);

    List<State> getStates(String proj_id);
}
