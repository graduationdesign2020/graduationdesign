package com.example.demo.Service;

import com.example.demo.DemoApplicationTests;
import com.example.demo.dao.ProjectDao;
import com.example.demo.dao.StateDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.entity.ProcessInfo;
import com.example.demo.entity.State;
import com.example.demo.entity.StateInfo;
import com.example.demo.service.ProcessService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessServiceTest extends DemoApplicationTests {
    @Autowired
    private ProcessService processService;

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private StateDao stateDao;

    @Autowired
    private StudentDao studentDao;

    @Test
    public void checkSelfProcess() {
        List<StateInfo> result = processService.checkSelfProcess("1");
        List<StateInfo> compare = new ArrayList<>();
//        List<State> states = stateDao.getStates("1");
//        int state = projectDao.getOne("1").getState();
//        State currentSta = stateDao.getOneByProjAndState("1", state);
//        compare.setStates(states);

        assertEquals(compare, result);
    }

    @Test
    public void checkProcess() {
        List<ProcessInfo> result = processService.checkProcess("101");
        List<ProcessInfo> compare = new ArrayList<>();

        assertEquals(compare, result);
    }
}
