package org.graduationdesign.gdmsservernotice.dao;

import org.graduationdesign.gdmsservernotice.entity.SchoolNotice;

import java.util.List;

public interface SchoolNoticeDao {
    SchoolNotice getSchoolNoticeById(int id);

    List<SchoolNotice> getSchoolNotices();

    List<SchoolNotice> getThreeSchoolNotices();

}
