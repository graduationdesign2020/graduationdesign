package com.example.demo.controller;

import com.example.demo.entity.DeptNotice;
import com.example.demo.entity.SchoolNotice;
import com.example.demo.entity.TeacherMessage;
import com.example.demo.service.NoticeService;
import com.example.demo.service.TeacherMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class NoticeController {
    @Autowired
    NoticeService noticeService;
    @Autowired
    TeacherMessageService teacherMessageService;

    @RequestMapping(path = "/getSchoolNotices")
    List<SchoolNotice> getSchoolNotices()
    {
        return noticeService.getSchoolNotices();
    }

    @RequestMapping(path = "/getThreeSchoolNotices")
    List<SchoolNotice> getThreeSchoolNotices()
    {
        return noticeService.getThreeSchoolNotices();
    }

    @RequestMapping(path = "/getSchoolNotice")
    public SchoolNotice getSchoolNotice(@RequestBody Map<String,Integer> params) {
        Integer id=params.get("id");
        return noticeService.getSchoolNoticeById(id);
    }

    @RequestMapping(path = "/getDepartmentNotices")
    public List<DeptNotice> getDeptNotices(@RequestBody Map<String,String> params)
    {
        String dept=params.get("dept");
        return noticeService.getDeptNoticesByDept(dept);
    }

    @RequestMapping(path = "/getThreeDepartmentNotice")
    public List<DeptNotice> getThreeDeptNoticesByDepartment(@RequestBody Map<String,String> params)
    {
        String dept=params.get("dept");
        return noticeService.getThreeDeptNoticesByDepartment(dept);
    }

    @RequestMapping(path = "/getDepartmentNotice")
    public DeptNotice getDeptNotice(@RequestBody Map<String,Integer> params) {
        Integer id=params.get("id");
        return noticeService.getDeptNoticeById(id);
    }

    @RequestMapping(path = "/getTeacherMessage")
    public TeacherMessage getTeacherMessage(@RequestBody Map<String,Integer> params) {
        Integer id=params.get("id");
        return teacherMessageService.getTeacherMessageById(id);
    }

    @RequestMapping(path = "/getTeacherMessages")
    public List<TeacherMessage> getTeacherMessages(@RequestBody Map<String,String> params) {
        String stu_id=params.get("student_id");
        return teacherMessageService.getTeacherMessages(stu_id);
    }

    @RequestMapping(path = "/sentMessage",method= RequestMethod.POST)
    public void sentMessage(@RequestBody Map<String,String> params) {
        String title= String.valueOf(params.get("title"));
        String teacher_id= String.valueOf(params.get("teacher_id"));
        String student_id= String.valueOf(params.get("student_id"));
        String content= String.valueOf(params.get("content"));
        teacherMessageService.sentTeacherMessage(title, teacher_id, student_id, content);
    }


}
