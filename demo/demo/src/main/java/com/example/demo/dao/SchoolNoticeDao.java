package com.example.demo.dao;

import com.example.demo.entity.SchoolNotice;

import java.util.List;
import java.util.Optional;

public interface SchoolNoticeDao {
    Optional<SchoolNotice> getSchoolNoticeById(int id);

    List<SchoolNotice> getSchoolNotices();

    List<SchoolNotice> getThreeSchoolNotices();

}
