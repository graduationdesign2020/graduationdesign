package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.ProcessInfo;
import com.example.demo.entity.StateInfo;
import com.example.demo.service.ProcessService;
import com.example.demo.utils.ReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
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
    //@ResponseBody
    public List<StateInfo> checkSelfProcess(@RequestBody Map<String, String> params){
        //System.out.println("self process stu_id");
        //System.out.println(params.get("stu_id"));
        return processService.checkSelfProcess(params.get("stu_id"));
    }

    @RequestMapping(path = "/checkProcess")
    //@ResponseBody
    public List<ProcessInfo> checkProcess(@RequestBody Map<String,String> params){
        System.out.println(processService.checkProcess(params.get("tea_id")));
        return processService.checkProcess(params.get("tea_id"));
    }

    @RequestMapping(path = "/setDeadline")
    public ReturnInfo setDeadline(@RequestBody Map<String,String> params){
        String time=params.get("time");
        List list= JSONObject.parseObject(params.get("students").toString(),List.class);
        List<String> student_id = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            student_id.add(JSONObject.parseObject(list.get(i).toString(),String.class));
        }
        int state=Integer.parseInt(params.get("state"));
        return processService.setDeadline(time,student_id,state);
    }
}
