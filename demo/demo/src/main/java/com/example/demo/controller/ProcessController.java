package com.example.demo.controller;



import com.example.demo.entity.Grade;

import com.example.demo.utils.ProcessInfo;
import com.example.demo.utils.StateInfo;
import com.example.demo.service.ProcessService;
import com.example.demo.utils.GradeInfo;
import com.example.demo.utils.ReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class ProcessController {
    @Autowired
    private ProcessService processService;

    @RequestMapping(path = "/checkSelfProcess")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT')")
    public List<StateInfo> checkSelfProcess(@RequestBody Map<String, String> params){
        //System.out.println("self process stu_id");
        //System.out.println(params.get("stu_id"));
        return processService.checkSelfProcess(params.get("stu_id"));
    }

    @RequestMapping(path = "/checkProcess")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    @ResponseBody
    public List<ProcessInfo> checkProcess(@RequestBody Map<String,String> params){
        List<ProcessInfo> processInfos = processService.checkProcess(params.get("tea_id"));
        System.out.println(processInfos);
        return processInfos;
    }

    @RequestMapping(path = "/setDeadline")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    @ResponseBody
    public ReturnInfo setDeadline(@RequestBody Map<String,String> params){
        String time=params.get("time");
        String teacher_id=params.get("teacher");
        int state=Integer.parseInt(params.get("state"));
        return processService.setDeadline(time,teacher_id,state);
    }

    @RequestMapping(path = "/getSelfGrade")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT')")
    @ResponseBody
    public Grade getGrade(@RequestBody Map<String,String> params){
        String id=params.get("id");
        return processService.getGradeById(id);
    }

    @RequestMapping(path = "/getGrades")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    @ResponseBody
    public List<GradeInfo> getGradeByteacher(@RequestBody Map<String,String> params){
        String id=params.get("id");
        return processService.getGradeByTeacher(id);
    }

    @RequestMapping(path = "/getStudentsProcess")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<ProcessInfo> getStudentsProcess(@RequestBody String dept) {
        return processService.getStudentsProcess(dept);
    }
}
