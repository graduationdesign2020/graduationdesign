package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.ProcessInfo;
import com.example.demo.entity.StateInfo;
import com.example.demo.service.ProcessService;
import com.example.demo.utils.ReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class ProcessController {
    @Autowired
    private ProcessService processService;

    @RequestMapping(path = "/checkSelfProcess")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT')")
    @ResponseBody
    public List<StateInfo> checkSelfProcess(@RequestBody Map<String, String> params){
        //System.out.println("self process stu_id");
        //System.out.println(params.get("stu_id"));
        return processService.checkSelfProcess(params.get("stu_id"));
    }

    @RequestMapping(path = "/checkProcess")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    @ResponseBody
    public List<ProcessInfo> checkProcess(@RequestBody Map<String,String> params){
        System.out.println(processService.checkProcess(params.get("tea_id")));
        return processService.checkProcess(params.get("tea_id"));
    }

    @RequestMapping(path = "/setDeadline")
    public ReturnInfo setDeadline(@RequestBody Map<String,String> params){
        String time=params.get("time");
        String teacher_id=params.get("teacher");
        int state=Integer.parseInt(params.get("state"));
        return processService.setDeadline(time,teacher_id,state);
    }
}
