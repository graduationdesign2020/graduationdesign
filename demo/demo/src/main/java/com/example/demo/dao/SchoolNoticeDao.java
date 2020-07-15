package com.example.demo.dao;

import com.example.demo.entity.SchoolNotice;

import java.util.List;

public interface SchoolNoticeDao {
    SchoolNotice getSchoolNoticeById(int id);

    List<SchoolNotice> getSchoolNotices();

}
