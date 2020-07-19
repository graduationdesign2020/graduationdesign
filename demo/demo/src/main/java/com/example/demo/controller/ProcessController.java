package com.example.demo.controller;

import com.example.demo.entity.ProcessInfo;
import com.example.demo.entity.StateInfo;
import com.example.demo.service.ProcessService;
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
    @ResponseBody
    public List<StateInfo> checkSelfProcess(@RequestBody Map<String, String> params){
        System.out.println("self process stu_id");
        System.out.println(params.get("stu_id"));
        return processService.checkSelfProcess(params.get("stu_id"));
    }

    @RequestMapping(path = "/checkProcess")
    @ResponseBody
    public List<ProcessInfo> checkProcess(@RequestBody Map<String,String> params){

        return processService.checkProcess(params.get("tea_id"));
    }
}
