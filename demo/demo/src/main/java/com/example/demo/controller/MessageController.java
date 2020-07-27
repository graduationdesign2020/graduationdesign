package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.ReadInfo;
import com.example.demo.entity.Student;
import com.example.demo.service.TeacherMessageService;
import com.example.demo.utils.MessageInfo;
import com.example.demo.utils.ReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public List<Student> teacherGetStudents(@RequestBody Map<String,String> params){
        String id=params.get("id");
        System.out.println(id);
        return teacherMessageService.getStudentsByTeacher_id(id);
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
    public List<MessageInfo> getTeacherMessages(@RequestBody Map<String,String> params) {
        String stu_id=params.get("student_id");
        return teacherMessageService.getTeacherMessages(stu_id);
    }

    @RequestMapping(path = "/getTeacherMessageRead")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    @ResponseBody
    public ReadInfo getTeacherMessageRead(@RequestBody Map<String,Integer> params) {
        return teacherMessageService.getTeacherMessageRead(params.get("id"));
    }

    @RequestMapping(path = "/teacherGetTeacherMessages")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    public List<MessageInfo> getTeacherMessagesByTeacher(@RequestBody Map<String,String> params) {
        String teacher_id=params.get("teacher_id");
        return teacherMessageService.getTeacherMessagesByTeacher_id(teacher_id);
    }

    @RequestMapping(path = "/teacherGetTeacherMessage")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    public MessageInfo getTeacherMessageByTeacher(@RequestBody Map<String,Integer> params){
        int id=params.get("id");
        return teacherMessageService.teacherGetTeacherMessageById(id);
    }


    @RequestMapping(path = "/sendMessages",method= RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_TEACHER')")
    public ReturnInfo sentMessage(@RequestBody Map<String,Object> params) {
        String title= String.valueOf(params.get("title"));
        String teacher_id= String.valueOf(params.get("id"));
        List list= JSONObject.parseObject(params.get("students").toString(),List.class);
        List<String> student_id = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            student_id.add(JSONObject.parseObject(list.get(i).toString(),String.class));
        }
        String content= String.valueOf(params.get("content"));
        return teacherMessageService.sentTeacherMessage(title, teacher_id, student_id, content);
    }
}

