package org.gdms.test.serviceimpl;

import org.gdms.test.dao.*;
import org.gdms.test.entity.*;
import org.gdms.test.service.ProcessService;
import org.gdms.test.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.gdms.test.constant.ReturnMsg.*;

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
    @Autowired
    private GradeDao gradeDao;

    @Override
    public List<StateInfo> checkSelfProcess(String stu_id) {
        List<StateInfo> stateInfos = new ArrayList<>();
        List<State> states = stateDao.getStates(stu_id);
        Optional<Project> project=projectDao.getOne(stu_id);
        if (project.isPresent()){
            Project p=project.get();
            String t=p.getTeacher_id();
            for (State state : states) {
                StateInfo stateInfo = new StateInfo();
                stateInfo.setSta(state);
                stateInfo.transfer();
                Timestamp timestamp=deadlineDao.getDeadline(t,state.getState());
                if(timestamp!=null)
                {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    String ddl = simpleDateFormat.format(new Date(timestamp.getTime()));
                    stateInfo.setEnd_time(ddl);
                }
                stateInfos.add(stateInfo);
            }
            int num = states.size();
            for (int i = num; i < 5; i++) {
                StateInfo stateInfo = new StateInfo();
                stateInfo.init(i);
                stateInfo.transfer();
                stateInfos.add(stateInfo);
            }
        }
        else {
            for (int i = 0; i < 5; i++) {
                StateInfo stateInfo = new StateInfo();
                stateInfo.init(i);
                stateInfo.transfer();
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
            Timestamp timestamp=deadlineDao.getDeadline(tea_id,i);
            if(timestamp!=null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String ddl = simpleDateFormat.format(new Date(timestamp.getTime()));
                processInfo.setEnd_time(ddl);
            }
            List<Student> studentsFinished = new ArrayList<>();
            List<Student> studentsUnfinished = new ArrayList<>();
            int finished = 0;
            for (Project project : projects) {
                State state = stateDao.getOneByProjAndState(project.getId(), i);
                if (state == null || state.getSubmit() != 5) {
                    //System.out.println(studentDao.getOne(project.getId()));
                    Student s=studentDao.getOne(project.getId());
                    Student student=new Student();
                    student.setId(s.getId());
                    student.setName(s.getName());
                    studentsUnfinished.add(student);
                }
                else if (state.getSubmit() == 5) {
                    finished++;
                    Student s=studentDao.getOne(project.getId());
                    Student student=new Student();
                    student.setId(s.getId());
                    student.setName(s.getName());
                    studentsFinished.add(student);
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
        }
        return processInfos;
    }

    @Override
    public ReturnInfo setDeadline(String end_time,String id,int state){
        Timestamp t=Timestamp.valueOf(end_time);
        Timestamp timestamp=deadlineDao.getDeadline(id,state);
        int i=0;
        if(timestamp==null) {
            i=deadlineDao.addDeadline(id, t, state);
//            Timestamp timestamp=Timestamp.valueOf(end_time);
//            long time=timestamp.getTime()-(long)1000*3600*24;
//            Timestamp date =new Timestamp(time);
//            LoadTask.timeTask(date,deadline.getId(),id,state);
        }
        else {
            i=deadlineDao.changeDeadline(id,t,state);
        }
        ReturnInfo returnInfo=new ReturnInfo();
        if(i==1)
        {
            returnInfo.setMsg(Msg1);
        }
        else {
            returnInfo.setMsg(Msg0);
        }
        return returnInfo;
    }

    @Override
    public Grade getGradeById(String id){
        return gradeDao.getById(id);
    }

    @Override
    public List<GradeInfo> getGradeByTeacher(String id) {
        List<String> list = projectDao.getIdByTeacher_id(id);
        List<GradeInfo> infoList = new ArrayList<>();
        for (String value : list) {
            Student student = studentDao.getOne(value);
            Grade grade = gradeDao.getById(value);
            GradeInfo gradeInfo = new GradeInfo();
            gradeInfo.setName(student.getName());
            if (grade != null) {
                gradeInfo.setGrade(grade);
            }
            infoList.add(gradeInfo);
        }
        return infoList;
    }
    public List<StuProInfo> getStudentsProcess(String dept) {
        List<Student> students = studentDao.findByDept(dept);
        List<StuProInfo> stuProInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            StuProInfo stuProInfo = new StuProInfo();
            stuProInfo.setState(i+1);
            List<StuInfo> stuInfos = new ArrayList<>();
            for (Student student : students) {
                StuInfo stuInfo = new StuInfo();
                State state = stateDao.getOneByProjAndState(student.getId(), i);
                if (state == null || state.getSubmit() != 5) {
                    stuInfo.setFlag(false);
                }
                else if (state.getSubmit() == 5) {
                    stuInfo.setFlag(true);
                }
                stuInfo.setId(student.getId());
                stuInfo.setName(student.getName());
                stuInfo.setDept(student.getDepartment());
                stuInfo.setMajor(student.getMajor());
                Project project = projectDao.getOne(student.getId()).get();
                stuInfo.setInstructor(project.getTeacher_id());
                stuInfo.setProject(project.getProject_name());
                stuInfos.add(stuInfo);
            }
            stuProInfo.setStuInfos(stuInfos);
            stuProInfos.add(stuProInfo);
        }
        return stuProInfos;
    }
}
