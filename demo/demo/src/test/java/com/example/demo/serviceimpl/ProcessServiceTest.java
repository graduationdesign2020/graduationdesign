package com.example.demo.serviceimpl;

import com.example.demo.DemoApplicationTests;
import com.example.demo.dao.ProjectDao;
import com.example.demo.dao.StateDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.entity.*;
import com.example.demo.service.ProcessService;
import com.example.demo.utils.ProcessInfo;
import com.example.demo.utils.ReturnInfo;
import com.example.demo.utils.SecurityInfo;
import com.example.demo.utils.StateInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void checkSelfProcess() throws JsonProcessingException {
        List<StateInfo> result = processService.checkSelfProcess("NTE2MDMwOTEwMzk1\n");
        List<StateInfo> compare = new ArrayList<>();
<<<<<<< HEAD
=======
        ObjectMapper om = new ObjectMapper();

        String s = "[{\"sta\":{\"id\":904,\"project_id\":\"NTE2MDMwOTEwMzk1\\n\",\"state\":0,\"submit\":5,\"start_time\":null,\"end_time\":null},\"state\":\"任务书\",\"stateNum\":0,\"submit\":5,\"start_time\":null,\"end_time\":null},{\"sta\":{\"id\":910,\"project_id\":\"NTE2MDMwOTEwMzk1\\n\",\"state\":4,\"submit\":5,\"start_time\":null,\"end_time\":null},\"state\":\"论文最终稿\",\"stateNum\":0,\"submit\":5,\"start_time\":null,\"end_time\":null},{\"sta\":{\"id\":923,\"project_id\":\"NTE2MDMwOTEwMzk1\\n\",\"state\":1,\"submit\":6,\"start_time\":null,\"end_time\":null},\"state\":\"开题报告\",\"stateNum\":0,\"submit\":6,\"start_time\":null,\"end_time\":null},{\"sta\":{\"id\":937,\"project_id\":\"NTE2MDMwOTEwMzk1\\n\",\"state\":3,\"submit\":5,\"start_time\":null,\"end_time\":null},\"state\":\"论文定稿\",\"stateNum\":0,\"submit\":5,\"start_time\":null,\"end_time\":null},{\"sta\":{\"id\":938,\"project_id\":\"NTE2MDMwOTEwMzk1\\n\",\"state\":2,\"submit\":6,\"start_time\":null,\"end_time\":null},\"state\":\"中期检查\",\"stateNum\":0,\"submit\":6,\"start_time\":null,\"end_time\":null}]";
        compare = om.readValue(s, new TypeReference<List<StateInfo>>() {});

        System.out.println(compare);
>>>>>>> 74d2dba31e186f149b0cd22be693e3bc1a4e35b2
        assertEquals(result, compare);
    }



    @Test
    public void checkSetDeadline(){
        List<String> student=new ArrayList<>();
        ReturnInfo result=processService.setDeadline("2020-7-23 12:00:00","1",2);
        assertEquals("FAIL",result.getMsg());
    }

    @Test
    public void checkProcess() {
        List<ProcessInfo> result = processService.checkProcess("09515");
        List<ProcessInfo> compare = new ArrayList<>();
        List<Project> projects = projectDao.findByTeacher("09515");
        int stuNum = projectDao.findByTeacher("09515").size();
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
                if (state == null || state.getSubmit() != 5) {
                    studentsUnfinished.add(studentDao.getOne(project.getId()));
                }
                else if (state.getSubmit() == 5) {
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
