package org.gdms.test.controller;


import org.gdms.test.entity.Grade;
import org.gdms.test.service.ProcessService;
import org.gdms.test.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class ProcessController {
    @Autowired
    private ProcessService processService;

    @RequestMapping(path = "/checkSelfProcess")
    public List<StateInfo> checkSelfProcess(@RequestBody Map<String,String> params){
        String id=params.get("id");
        return processService.checkSelfProcess(id);
    }

    @RequestMapping(path = "/checkProcess")
    public List<ProcessInfo> checkProcess(@RequestBody Map<String,String> params){
        String id=params.get("id");
        return processService.checkProcess(id);
    }

    @RequestMapping(path = "/setDeadline")
    @ResponseBody
    public ReturnInfo setDeadline(@RequestBody Map<String,String> params){
        String id=params.get("id");
        String time=params.get("time");
        int state=Integer.parseInt(params.get("state"));
        return processService.setDeadline(time,id,state);
    }

    @RequestMapping(path = "/getSelfGrade")
    public Grade getGrade(@RequestBody Map<String,String> params){
        String id=params.get("id");
        return processService.getGradeById(id);
    }

    @RequestMapping(path = "/getGrades")
    public List<GradeInfo> getGradeByteacher(@RequestBody Map<String,String> params){
        String id=params.get("id");
        return processService.getGradeByTeacher(id);
    }

    @RequestMapping(path = "/getStudentsProcess")
    public List<StuProInfo> getStudentsProcess(@RequestBody String dept) {
        System.out.println(dept);
        return processService.getStudentsProcess(dept);
    }
}
