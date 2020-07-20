package com.example.demo.controller;

import com.example.demo.entity.DeptNotice;
import com.example.demo.entity.SchoolNotice;
import com.example.demo.service.NoticeService;
import com.example.demo.service.TeacherMessageService;
import com.example.demo.utils.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping(path = "/getSchoolNotices")
    public List<SchoolNotice> getSchoolNotices()
    {
        return noticeService.getSchoolNotices();
    }

    @RequestMapping(path = "/getThreeSchoolNotices")
    public List<SchoolNotice> getThreeSchoolNotices()
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

    @RequestMapping(path = "/getThreeDepartmentNotices")
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
}
