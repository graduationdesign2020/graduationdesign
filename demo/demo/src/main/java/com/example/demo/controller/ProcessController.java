package com.example.demo.controller;



import com.example.demo.entity.Grade;

import com.example.demo.utils.ProcessInfo;
import com.example.demo.utils.StateInfo;
import com.example.demo.service.ProcessService;
import com.example.demo.utils.GradeInfo;
import com.example.demo.utils.ReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    public List<StateInfo> checkSelfProcess(Authentication authentication){
        return processService.checkSelfProcess(authentication.getName());
    }

    @RequestMapping(path = "/checkProcess")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    public List<ProcessInfo> checkProcess(Authentication authentication){
        return processService.checkProcess(authentication.getName());
    }

    @RequestMapping(path = "/setDeadline")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    @ResponseBody
    public ReturnInfo setDeadline(@RequestBody Map<String,String> params, Authentication authentication){
        String time=params.get("time");
        int state=Integer.parseInt(params.get("state"));
        return processService.setDeadline(time,authentication.getName(),state);
    }

    @RequestMapping(path = "/getSelfGrade")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT')")
    public Grade getGrade(Authentication authentication){
        return processService.getGradeById(authentication.getName());
    }

    @RequestMapping(path = "/getGrades")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    public List<GradeInfo> getGradeByteacher(Authentication authentication){
        return processService.getGradeByTeacher(authentication.getName());
    }

    @RequestMapping(path = "/getStudentsProcess")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<ProcessInfo> getStudentsProcess(@RequestBody String dept) {
        return processService.getStudentsProcess(dept);
    }
}
