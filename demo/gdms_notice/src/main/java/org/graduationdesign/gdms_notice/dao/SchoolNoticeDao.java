package org.graduationdesign.gdms_notice.dao;

import org.graduationdesign.gdms_notice.entity.SchoolNotice;

import java.util.List;

public interface SchoolNoticeDao {
    SchoolNotice getSchoolNoticeById(int id);

    List<SchoolNotice> getSchoolNotices();

    List<SchoolNotice> getThreeSchoolNotices();

}
