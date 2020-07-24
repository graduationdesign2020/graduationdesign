package com.example.demo.serviceimpl;

import com.example.demo.DemoApplicationTests;
import com.example.demo.dao.ProjectDao;
import com.example.demo.dao.StateDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.entity.*;
import com.example.demo.service.ProcessService;
import com.example.demo.utils.ReturnInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Rollback
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
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
    public void checkSelfProcess() {
        List<StateInfo> result = processService.checkSelfProcess("1");
        List<StateInfo> compare = new ArrayList<>();
        assertEquals(result, compare);
    }



    @Test
    @Transactional
    public void checkSetDeadline(){
        List<String> student=new ArrayList<>();
        ReturnInfo result=processService.setDeadline("2020-7-23 12:00:00","1",2);
        assertEquals("FAIL",result.getMsg());
    }

    @Test
    public void checkProcess() {
        List<ProcessInfo> result = processService.checkProcess("1");
        List<ProcessInfo> compare = new ArrayList<>();
        List<Project> projects = projectDao.findByTeacher("1");
        int stuNum = projectDao.findByTeacher("1").size();
        for (int i = 0; i < 5; i++) {
            ProcessInfo processInfo = new ProcessInfo();

            String name = "";
            switch (i) {
                case 0: name = "任务书";break;
                case 1: name = "开题报告";break;
                case 2: name = "中期检查";break;
                case 3: name = "论文定稿";break;
                case 4: name = "论文最终稿";
            }
            processInfo.setName(name);
            List<Student> studentsFinished = new ArrayList<>();
            List<Student> studentsUnfinished = new ArrayList<>();
            int finished = 0;
            for (Project project : projects) {
                State state = stateDao.getOneByProjAndState(project.getId(), i);
                if (state == null || state.getSubmit() != 1) {
                    studentsUnfinished.add(studentDao.getOne(project.getId()));
                }
                else if (state.getSubmit() == 1) {
                    finished++;
                    studentsFinished.add(studentDao.getOne(project.getId()));
                }
            }
            int unfinished = stuNum - finished;
            processInfo.setFinished(finished);
            processInfo.setUnfinished(unfinished);
            processInfo.setFinishedStu(studentsFinished);
            processInfo.setUnfinishedStu(studentsUnfinished);
            compare.add(processInfo);
        }

        assertEquals(result, compare);
    }
}
