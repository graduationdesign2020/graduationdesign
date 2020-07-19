package com.example.demo.serviceimpl;

import com.example.demo.dao.ProjectDao;
import com.example.demo.dao.StateDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.entity.*;
import com.example.demo.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    private StateDao stateDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private StudentDao studentDao;

    @Override
    public StateInfo checkSelfProcess(String stu_id) {
        StateInfo stateInfo = new StateInfo();
        stateInfo.setStates(stateDao.getStates(stu_id));
        stateInfo.setCurrentState(stateDao.getOneByProjAndState(stu_id, projectDao.getOne(stu_id).getState()));
        return stateInfo;
    }

    @Override
    public List<ProcessInfo> checkProcess(String tea_id) {
        List<ProcessInfo> processInfos = new ArrayList<>();
        List<Project> projects = projectDao.findByTeacher(tea_id);
        int stuNum = projectDao.findByTeacher(tea_id).size();
        for (int i = 1; i <= 5; i++) {
            ProcessInfo processInfo = new ProcessInfo();
            processInfo.setName("process"+i);
            List<Student> studentsFinished = new ArrayList<>();
            List<Student> studentsUnfinished = new ArrayList<>();
            int finished = 0;
            for (Project project : projects) {
                State state = stateDao.getOneByProjAndState(project.getId(), i);
                if (state.getSubmit() == 1) {
                    finished++;
                    studentsFinished.add(studentDao.getOne(project.getId()));
                }
                else {
                    studentsUnfinished.add(studentDao.getOne(project.getId()));
                }
            }
            int unfinished = stuNum - finished;
            processInfo.setFinished(finished);
            processInfo.setUnfinished(unfinished);
            processInfo.setFinishedStu(studentsFinished);
            processInfo.setUnfinishedStu(studentsUnfinished);
            processInfos.add(processInfo);
        }
        return processInfos;
    }
}
