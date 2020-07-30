package com.example.demo.controller;

import com.example.demo.entity.DeptNotice;
import com.example.demo.entity.SchoolNotice;
import com.example.demo.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping(path = "/getSchoolNotices")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_TEACHER')")
    public List<SchoolNotice> getSchoolNotices()
    {
        return noticeService.getSchoolNotices();
    }

    @RequestMapping(path = "/getThreeSchoolNotices")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_TEACHER')")
    public List<SchoolNotice> getThreeSchoolNotices()
    {
        return noticeService.getThreeSchoolNotices();
    }

    @RequestMapping(path = "/getSchoolNotice")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_TEACHER')")
    public SchoolNotice getSchoolNotice(@RequestBody Map<String,Integer> params) {
        Integer id=params.get("id");
        System.out.println(id);
        return noticeService.getSchoolNoticeById(id);
    }

    @RequestMapping(path = "/getDepartmentNotices")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_TEACHER')")
    public List<DeptNotice> getDeptNotices(Authentication authentication)
    {
        System.out.println(authentication.getAuthorities().toString());
        System.out.println(authentication.getAuthorities().toArray()[0].toString());
        if(authentication.getAuthorities().toArray()[0].toString().equals("ROLE_STUDENT")){
            System.out.println(1);
            return noticeService.getDeptNoticesBySid(authentication.getName());
        }
        if(authentication.getAuthorities().toArray()[0].toString().equals("ROLE_TEACHER")){
            System.out.println(2);
            return noticeService.getDeptNoticesByTid(authentication.getName());
        }
        return null;
    }

    @RequestMapping(path = "/getThreeDepartmentNotices")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_TEACHER')")
    public List<DeptNotice> getThreeDeptNoticesByDepartment(Authentication authentication)
    {
        System.out.println(authentication.getAuthorities().toString());
        if(authentication.getAuthorities().toArray()[0].toString().equals("ROLE_STUDENT"))
            return noticeService.getThreeDeptNoticesBySid(authentication.getName());
        if(authentication.getAuthorities().toArray()[0].toString().equals("ROLE_TEACHER"))
            return noticeService.getThreeDeptNoticesByTid(authentication.getName());
        return null;
    }

    @RequestMapping(path = "/getDepartmentNotice")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_TEACHER')")
    public DeptNotice getDeptNotice(@RequestBody Map<String,String> params){
        Integer id=Integer.parseInt(params.get("id"));
        return noticeService.getDeptNoticeById(id);
    }
}
