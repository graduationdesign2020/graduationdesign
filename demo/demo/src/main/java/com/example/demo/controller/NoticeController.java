package com.example.demo.controller;

import com.example.demo.entity.DeptNotice;
import com.example.demo.entity.SchoolNotice;
import com.example.demo.entity.TeacherMessage;
import com.example.demo.service.NoticeService;
import com.example.demo.service.TeacherMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(path = "/getDepartmentNotices")
    List<DeptNotice> getDeptNotices(@RequestParam("dept") String dept)
    {
        return noticeService.getDeptNoticesByDept(dept);
    }

    @RequestMapping(path = "/getSchoolNotice")
    public SchoolNotice getSchoolNotice(@RequestParam("id") Integer id) {
        return noticeService.getSchoolNoticeById(id);
    }

    @RequestMapping(path = "/getDepartmentNotice")
    public DeptNotice getDeptNotice(@RequestParam("id") Integer id) {
        return noticeService.getDeptNoticeById(id);
    }

    @RequestMapping(path = "/getTeacherMessage")
    public TeacherMessage getTeacherMessage(@RequestParam("id") Integer id) {
        return teacherMessageService.getTeacherMessageById(id);
    }

    @RequestMapping(path = "/getTeacherMessages")
    public List<TeacherMessage> getDeptNotice(@RequestParam("id") String stu_id) {
        return teacherMessageService.getTeacherMessages(stu_id);
    }

    @RequestMapping(path = "/sentMessage",method= RequestMethod.POST)
    public void sentMessage(@RequestParam("title") String title,@RequestParam("teacher_id") String teacher_id,@RequestParam("student_id") String student_id,@RequestParam("content") String content) {
        teacherMessageService.sentTeacherMessage(title, teacher_id, student_id, content);
    }


}
