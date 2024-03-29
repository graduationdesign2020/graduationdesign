package org.graduationdesign.gdmsservercore.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.graduationdesign.gdmsservercore.entity.Student;
import org.graduationdesign.gdmsservercore.service.TeacherMessageService;
import org.graduationdesign.gdmsservercore.utils.MessageInfo;
import org.graduationdesign.gdmsservercore.utils.ReadInfo;
import org.graduationdesign.gdmsservercore.utils.ReturnInfo;
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
    public ReturnInfo sentMessage(@RequestBody JSONObject params, Authentication authentication) {
        String title = String.valueOf(params.get("title"));
        String teacher_id = String.valueOf(params.get("id"));
        JSONArray students= params.getJSONArray("students");
        System.out.println(teacher_id);
        List<String> student_id = new ArrayList<>();
        for (Object student : students) {
            student_id.add(JSONObject.parseObject(student.toString(), String.class));
        }
        String content = String.valueOf(params.get("content"));
//        System.out.println(content);
        JSONArray tasks= params.getJSONArray("tasks");
//        System.out.println(tasks);
        List<String> keys = new ArrayList<>();
        for (Object o : tasks) {
            keys.add(o.toString());
        }
//        System.out.println(keys);
        return teacherMessageService.sentTeacherMessage(title, teacher_id, student_id, content,keys);
    }
}

