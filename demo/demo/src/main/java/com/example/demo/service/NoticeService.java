package com.example.demo.service;

import com.example.demo.entity.DeptNotice;
import com.example.demo.entity.SchoolNotice;

import java.util.List;

public interface NoticeService {
    List<DeptNotice> getDeptNoticesByDept(String department);

    DeptNotice getDeptNoticeById(int id);

    SchoolNotice getSchoolNoticeById(int id);

    List<SchoolNotice> getSchoolNotices();

    List<SchoolNotice> getThreeSchoolNotices();

    List<DeptNotice> getThreeDeptNoticesByDepartment(String dept);
}
