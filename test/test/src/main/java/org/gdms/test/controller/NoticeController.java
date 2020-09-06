package org.gdms.test.controller;

import org.gdms.test.entity.DeptNotice;
import org.gdms.test.entity.SchoolNotice;
import org.gdms.test.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        System.out.println(id);
        return noticeService.getSchoolNoticeById(id);
    }

    @RequestMapping(path = "/getDepartmentNotices")
    public List<DeptNotice> getDeptNotices(@RequestBody Map<String,String> params)
    {
        String id=params.get("id");
        int auth=Integer.parseInt(params.get("auth"));
        if(auth==0){
            System.out.println(1);
            return noticeService.getDeptNoticesBySid(id);
        }
        if(auth==1){
            System.out.println(2);
            return noticeService.getDeptNoticesByTid(id);
        }
        return null;
    }

    @RequestMapping(path = "/getThreeDepartmentNotices")
    public List<DeptNotice> getThreeDeptNoticesByDepartment(@RequestBody Map<String,String> params)
    {
        String id=params.get("id");
        int auth=Integer.parseInt(params.get("auth"));
        if(auth==0)
            return noticeService.getThreeDeptNoticesBySid(id);
        if(auth==1)
            return noticeService.getThreeDeptNoticesByTid(id);
        return null;
    }

    @RequestMapping(path = "/getDepartmentNotice")
    public DeptNotice getDeptNotice(@RequestBody Map<String,String> params){
        int id=Integer.parseInt(params.get("id"));
        return noticeService.getDeptNoticeById(id);
    }
}
