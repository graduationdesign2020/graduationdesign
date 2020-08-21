package com.example.demo.serviceimpl;

import com.example.demo.DemoApplicationTests;
import com.example.demo.dao.ProjectDao;
import com.example.demo.dao.StateDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.entity.*;
import com.example.demo.service.ProcessService;
import com.example.demo.utils.*;
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
        String student="305349154743";
        List<StateInfo> result = processService.checkSelfProcess(student);
        List<StateInfo> compare = new ArrayList<>();
        ObjectMapper om = new ObjectMapper();

        StateInfo stateInfo=new StateInfo();
        State state=new State();
        state.setId(2845);
        state.setProject_id(student);
        state.setState(0);
        state.setSubmit(5);
        stateInfo.setSta(state);
        stateInfo.setState("任务书");
        stateInfo.setSubmit(state.getSubmit());
        stateInfo.setEnd_time(null);
        compare.add(stateInfo);

        StateInfo stateInfo1=new StateInfo();
        State state1=new State();
        state1.setId(3313);
        state1.setProject_id(student);
        state1.setState(1);
        state1.setSubmit(6);
        stateInfo1.setSta(state1);
        stateInfo1.setState("开题报告");
        stateInfo1.setSubmit(state1.getSubmit());
        stateInfo1.setEnd_time(null);
        compare.add(stateInfo1);

        StateInfo stateInfo2=new StateInfo();
        State state2=new State();
        state2.setId(3229);
        state2.setProject_id(student);
        state2.setState(2);
        state2.setSubmit(6);
        stateInfo2.setSta(state2);
        stateInfo2.setState("中期检查");
        stateInfo2.setSubmit(state2.getSubmit());
        stateInfo2.setEnd_time(null);
        compare.add(stateInfo2);
        StateInfo stateInfo3=new StateInfo();
        State state3=new State();
        state3.setId(2425);
        state3.setProject_id(student);
        state3.setState(3);
        state3.setSubmit(5);
        stateInfo3.setSta(state3);
        stateInfo3.setState("论文定稿");
        stateInfo3.setSubmit(state3.getSubmit());
        stateInfo3.setEnd_time(null);
        compare.add(stateInfo3);
        StateInfo stateInfo4=new StateInfo();
        State state4=new State();
        state4.setId(1813);
        state4.setProject_id(student);
        state4.setState(4);
        state4.setSubmit(5);
        stateInfo4.setSta(state4);
        stateInfo4.setState("论文最终稿");
        stateInfo4.setSubmit(state4.getSubmit());
        stateInfo4.setEnd_time(null);
        compare.add(stateInfo4);
        System.out.println(compare);
        assertEquals(result, compare);
    }


    @Transactional
    @Test
    public void checkSetDeadline(){
        List<String> student=new ArrayList<>();
        ReturnInfo result=processService.setDeadline("2020-8-30 12:00:00","03047a",2);
        assertEquals("SUCCESS",result.getMsg());
    }

    @Test
    public void checkGetGrade(){
        Grade grade=processService.getGradeById("305349154743");
        Grade compare=new Grade();
        compare.setId("305349154743");
        compare.setTeachergrade("B-");
        compare.setReviewgrade("B");
        compare.setAllgrade("");
        compare.setThesisgrade("");
        assertEquals(compare,grade);
    }

    @Test
    public void checkgetGradeByTeacher(){
        List<GradeInfo> infoList = processService.getGradeByTeacher("03047a");
        assertEquals(5,infoList.size());
    }

    @Test
    public void checkgetStudentsProcess(){
        List<StuProInfo> processInfos=processService.getStudentsProcess("111");
        assertEquals(5,processInfos.size());
    }

    @Test
    public void checkProcess(){
        List<ProcessInfo> list=processService.checkProcess("03047a");
        assertEquals(5,list.size());
    }

//    @Test
//    public void checkProcess() {
//        List<ProcessInfo> result = processService.checkProcess("09515");
//        List<ProcessInfo> compare = new ArrayList<>();
//        List<Project> projects = projectDao.findByTeacher("09515");
//        int stuNum = projectDao.findByTeacher("09515").size();
//        for (int i = 0; i < 5; i++) {
//            ProcessInfo processInfo = new ProcessInfo();
//
//            String name = "";
//            switch (i) {
//                case 0: name = "任务书";break;
//                case 1: name = "开题报告";break;
//                case 2: name = "中期检查";break;
//                case 3: name = "论文定稿";break;
//                case 4: name = "论文最终稿";
//            }
//            processInfo.setName(name);
//            List<Student> studentsFinished = new ArrayList<>();
//            List<Student> studentsUnfinished = new ArrayList<>();
//            int finished = 0;
//            for (Project project : projects) {
//                State state = stateDao.getOneByProjAndState(project.getId(), i);
//                if (state == null || state.getSubmit() != 5) {
//                    studentsUnfinished.add(studentDao.getOne(project.getId()));
//                }
//                else if (state.getSubmit() == 5) {
//                    finished++;
//                    studentsFinished.add(studentDao.getOne(project.getId()));
//                }
//            }
//            int unfinished = stuNum - finished;
//            processInfo.setFinished(finished);
//            processInfo.setUnfinished(unfinished);
//            processInfo.setFinishedStu(studentsFinished);
//            processInfo.setUnfinishedStu(studentsUnfinished);
//            compare.add(processInfo);
//        }
//
//        assertEquals(result, compare);
//    }
}
