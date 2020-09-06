package org.gdms.test.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.gdms.test.entity.Student;
import org.gdms.test.service.TeacherMessageService;
import org.gdms.test.util.*;
import org.springframework.beans.factory.annotation.Autowired;
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
        String id=params.get("id");
        int auth=Integer.parseInt(params.get("auth"));
        if(auth==0){
            System.out.println(1);
            return teacherMessageService.getTeacherMessages(id);
        }
        if(auth==1){
            System.out.println(2);
            return teacherMessageService.getTeacherMessagesByTeacher_id(id);
        }
        return null;
    }

    @RequestMapping(path = "/getTeacherMessageRead")
    @ResponseBody
    public ReadInfo getTeacherMessageRead(@RequestBody Map<String,Integer> params) {
        return teacherMessageService.getTeacherMessageRead(params.get("id"));
    }

    @RequestMapping(path = "/teacherGetTeacherMessage")
    public MessageInfo getTeacherMessageByTeacher(@RequestBody Map<String,Integer> params){
        int id=params.get("id");
        return teacherMessageService.teacherGetTeacherMessageById(id);
    }


    @RequestMapping(path = "/sendMessages",method= RequestMethod.POST)
    public ReturnInfo sentMessage(@RequestBody JSONObject params) {
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

