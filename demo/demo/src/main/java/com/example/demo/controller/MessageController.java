package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.ReadInfo;
import com.example.demo.entity.Student;
import com.example.demo.service.TeacherMessageService;
import com.example.demo.utils.MessageInfo;
import com.example.demo.utils.ReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class MessageController {
    @Autowired
    TeacherMessageService teacherMessageService;

    @RequestMapping(path = "/teacherGetStudents")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    public List<Student> teacherGetStudents(Authentication authentication){
        return teacherMessageService.getStudentsByTeacher_id(authentication.getName());
    }
    @RequestMapping(path = "/getTeacherMessage")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_TEACHER')")
    public MessageInfo getTeacherMessage(@RequestBody Map<String,Integer> params) {
        Integer id=params.get("id");
        Integer reading_id=params.get("reading_id");
        return teacherMessageService.getTeacherMessageById(id,reading_id);
    }

    @RequestMapping(path = "/getTeacherMessages")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_TEACHER')")
    public List<MessageInfo> getTeacherMessages(Authentication authentication) {
        if(authentication.getAuthorities().toArray()[0].toString().equals("ROLE_STUDENT")){
            System.out.println(1);
            return teacherMessageService.getTeacherMessages(authentication.getName());
        }
        if(authentication.getAuthorities().toArray()[0].toString().equals("ROLE_TEACHER")){
            System.out.println(2);
            return teacherMessageService.getTeacherMessagesByTeacher_id(authentication.getName());
        }
        return null;
    }

    @RequestMapping(path = "/getTeacherMessageRead")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    @ResponseBody
    public ReadInfo getTeacherMessageRead(@RequestBody Map<String,Integer> params) {
        return teacherMessageService.getTeacherMessageRead(params.get("id"));
    }

    @RequestMapping(path = "/teacherGetTeacherMessage")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    public MessageInfo getTeacherMessageByTeacher(@RequestBody Map<String,Integer> params){
        int id=params.get("id");
        return teacherMessageService.teacherGetTeacherMessageById(id);
    }


    @RequestMapping(path = "/sendMessages",method= RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    public ReturnInfo sentMessage(@RequestBody Map<String,Object> params, Authentication authentication) {
        String title= String.valueOf(params.get("title"));
        String teacher_id= authentication.getName();
        List students= JSONObject.parseObject(params.get("students").toString(),List.class);
        List<String> student_id = new ArrayList<>();
        for (Object student : students) {
            student_id.add(JSONObject.parseObject(student.toString(), String.class));
        }
        String content= String.valueOf(params.get("content"));
//        System.out.println(content);
        List tasks= JSONObject.parseObject(params.get("tasks").toString(),List.class);
        List<String> keys= new ArrayList<>();
        for (Object key : keys) {
            keys.add(JSONObject.parseObject(key.toString(), String.class));
        }
        return teacherMessageService.sentTeacherMessage(title, teacher_id, student_id, content,keys);
    }
}

