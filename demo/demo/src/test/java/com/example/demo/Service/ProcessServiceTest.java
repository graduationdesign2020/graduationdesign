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
import org.springframework.transaction.annotation.Transactional;

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
    public void contextLoads() {
    }

    @Test
    @Transactional
    public void checkSelfProcess() {
        List<StateInfo> result = processService.checkSelfProcess("1");
        List<StateInfo> compare = new ArrayList<>();
        State state1 = new State(), state2 = new State();
        state1.init(1, "1", 0, 1, "2020-07-19 23:39:51", "2020-07-19 23:39:57");
        state2.init(2, "1", 1, 1, "2020-07-19 23:40:11", "2020-07-19 23:40:14");
        StateInfo stateInfo1 = new StateInfo(), stateInfo2 = new StateInfo(), stateInfo3 = new StateInfo(),
                stateInfo4 = new StateInfo(), stateInfo5 = new StateInfo();
        stateInfo1.setSta(state1);
        stateInfo2.setSta(state2);
        stateInfo3.init(2);
        stateInfo4.init(3);
        stateInfo5.init(4);
        stateInfo1.transfer();
        stateInfo2.transfer();
        stateInfo3.transfer();
        stateInfo4.transfer();
        stateInfo5.transfer();
        compare.add(stateInfo1);
        compare.add(stateInfo2);
        compare.add(stateInfo3);
        compare.add(stateInfo4);
        compare.add(stateInfo5);

        assertEquals(result, compare);
    }

    @Test
    @Transactional
    public void checkProcess() {
        List<ProcessInfo> result = processService.checkProcess("1");
        List<ProcessInfo> compare = new ArrayList<>();


        assertEquals(result, compare);
    }
}
