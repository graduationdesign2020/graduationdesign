package com.example.demo.controller;

import com.example.demo.entity.ReadInfo;
import com.example.demo.entity.Student;
import com.example.demo.entity.TeacherMessage;
import com.example.demo.service.TeacherMessageService;
import com.example.demo.utils.MessageInfo;
import com.example.demo.utils.ReturnInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class MessageController {
    @Autowired
    TeacherMessageService teacherMessageService;
    @RequestMapping(path = "/teacherGetStudents")
    public List<Student> teacherGetStudents(@RequestBody Map<String,String> params){
        String id=params.get("id");
        return teacherMessageService.getStudentsByTeacher_id(id);
    }
    @RequestMapping(path = "/getTeacherMessage")
    public MessageInfo getTeacherMessage(@RequestBody Map<String,Integer> params) {
        Integer id=params.get("id");
        Integer reading_id=params.get("reading_id");
        return teacherMessageService.getTeacherMessageById(id,reading_id);
    }

    @RequestMapping(path = "/getTeacherMessages")
    public List<MessageInfo> getTeacherMessages(@RequestBody Map<String,String> params) {
        String stu_id=params.get("student_id");
        return teacherMessageService.getTeacherMessages(stu_id);
    }

    @RequestMapping(path = "getTeacherMessageRead")
    @ResponseBody
    public ReadInfo getTeacherMessageRead(@RequestBody int id) {
        return teacherMessageService.getTeacherMessageRead(id);
    }

    @RequestMapping(path = "/teacherGetTeacherMessages")
    public List<MessageInfo> getTeacherMessagesByTeacher(@RequestBody Map<String,String> params) {
        String teacher_id=params.get("teacher_id");
        return teacherMessageService.getTeacherMessagesByTeacher_id(teacher_id);
    }

    @RequestMapping(path = "/teacherGetTeacherMessage")
    public MessageInfo getTeacherMessageByTeacher(@RequestBody Map<String,Integer> params){
        int id=params.get("id");
        return teacherMessageService.teacherGetTeacherMessageById(id);
    }

    @RequestMapping(path = "/sendMessages",method= RequestMethod.POST)
    public ReturnInfo sentMessage(@RequestBody Map<String,String> params) {
        String title= String.valueOf(params.get("title"));
        String teacher_id= String.valueOf(params.get("id"));
        List<String> student_id= List.of(params.get("students"));
        String content= String.valueOf(params.get("content"));
        return teacherMessageService.sentTeacherMessage(title, teacher_id, student_id, content);
    }
}

