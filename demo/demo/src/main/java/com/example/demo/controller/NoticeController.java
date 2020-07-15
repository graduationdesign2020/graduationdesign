package com.example.demo.controller;

import com.example.demo.entity.DeptNotice;
import com.example.demo.entity.SchoolNotice;
import com.example.demo.entity.TeacherMessage;
import com.example.demo.service.NoticeService;
import com.example.demo.service.TeacherMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class NoticeController {
    @Autowired
    NoticeService noticeService;

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


}
