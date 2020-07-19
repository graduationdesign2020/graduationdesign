package com.example.demo.controller;

import com.example.demo.entity.ProcessInfo;
import com.example.demo.entity.StateInfo;
import com.example.demo.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class ProcessController {
    @Autowired
    private ProcessService processService;

    @RequestMapping(path = "/checkSelfProcess")
    @ResponseBody
    public List<StateInfo> checkSelfProcess(@RequestBody String stu_id){
        return processService.checkSelfProcess(stu_id);
    }

    @RequestMapping(path = "/checkProcess")
    @ResponseBody
    public List<ProcessInfo> checkProcess(String tea_id){
        return processService.checkProcess(tea_id);
    }
}
