package org.graduationdesign.gdmsservercore.service;


import org.graduationdesign.gdmsservercore.entity.Grade;
import org.graduationdesign.gdmsservercore.utils.*;

import java.util.List;

public interface ProcessService {
    List<StateInfo> checkSelfProcess(String stu_id);
    List<ProcessInfo> checkProcess(String tea_id);
    List<StuProInfo> getStudentsProcess(String dept);
    ReturnInfo setDeadline(String end_time, String id, int state);
    Grade getGradeById(String id);
    List<GradeInfo> getGradeByTeacher(String id);
}
