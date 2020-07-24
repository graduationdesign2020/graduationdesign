package com.example.demo.serviceimpl;

import com.example.demo.dao.DeadlineDao;
import com.example.demo.dao.ProjectDao;
import com.example.demo.dao.StateDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.entity.*;
import com.example.demo.schdule.LoadTask;
import com.example.demo.service.ProcessService;
import com.example.demo.utils.ReturnInfo;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.constant.ReturnMsg.*;

@Service
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    private StateDao stateDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private DeadlineDao deadlineDao;

    @Override
    public List<StateInfo> checkSelfProcess(String stu_id) {
        List<StateInfo> stateInfos = new ArrayList<>();
        List<State> states = stateDao.getStates(stu_id);
        Optional<Project> project=projectDao.getOne(stu_id);
        if (project.isPresent()){
            Project p=project.get();
            String t=p.getTeacher_id();
            if (states.isEmpty()) {
                return null;
            }
            for (State state : states) {
                StateInfo stateInfo = new StateInfo();
                stateInfo.setSta(state);
                stateInfo.transfer();
                stateInfos.add(stateInfo);
            }
            int num = states.size();
            for (int i = num; i < 5; i++) {
                StateInfo stateInfo = new StateInfo();
                stateInfo.init(i);
                stateInfo.transfer();
                String ddl=deadlineDao.getDeadline(t,i);
                stateInfo.setEnd_time(ddl);
                stateInfos.add(stateInfo);
            }
        }
        return stateInfos;
    }

    @Override
    public List<ProcessInfo> checkProcess(String tea_id) {
        System.out.println("check process");
        List<ProcessInfo> processInfos = new ArrayList<>();
        List<Project> projects = projectDao.findByTeacher(tea_id);
        System.out.println(projects);
        int stuNum = projectDao.findByTeacher(tea_id).size();
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
            String ddl=deadlineDao.getDeadline(tea_id,i);
            processInfo.setEnd_time(ddl);
            List<Student> studentsFinished = new ArrayList<>();
            List<Student> studentsUnfinished = new ArrayList<>();
            int finished = 0;
            for (Project project : projects) {
                State state = stateDao.getOneByProjAndState(project.getId(), i);
                if (state == null || state.getSubmit() != 1) {
                    //System.out.println(studentDao.getOne(project.getId()));
                    studentsUnfinished.add(studentDao.getOne(project.getId()));
                }
                else if (state.getSubmit() == 1) {
                    finished++;
                    studentsFinished.add(studentDao.getOne(project.getId()));
                }
            }
            int unfinished = stuNum - finished;
            //System.out.println(studentsUnfinished);
            processInfo.setFinished(finished);
            processInfo.setUnfinished(unfinished);
            processInfo.setFinishedStu(studentsFinished);
            processInfo.setUnfinishedStu(studentsUnfinished);
            //System.out.println(processInfo);
            processInfos.add(processInfo);
            //System.out.println(processInfos);
        }
        return processInfos;
    }

    @Override
    public ReturnInfo setDeadline(String end_time,String id,int state){
        Timestamp ddl=Timestamp.valueOf(end_time);
        Deadline deadline=deadlineDao.addDeadline(id,ddl,state);
        ReturnInfo returnInfo=new ReturnInfo();
        if(deadline!=null) {
            returnInfo.setMsg(Msg1);
            Timestamp timestamp=Timestamp.valueOf(end_time);
            long time=timestamp.getTime()-(long)1000*3600*24;
            Timestamp date =new Timestamp(time);
            LoadTask.timeTask(date,deadline.getId(),id,state);
        }
        else {
            returnInfo.setMsg(Msg0);
        }
        return returnInfo;
    }
}
