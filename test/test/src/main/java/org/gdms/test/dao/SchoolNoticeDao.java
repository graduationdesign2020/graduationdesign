package org.gdms.test.dao;

import org.gdms.test.entity.SchoolNotice;

import java.util.List;

public interface SchoolNoticeDao {
    SchoolNotice getSchoolNoticeById(int id);

    List<SchoolNotice> getSchoolNotices();

    List<SchoolNotice> getThreeSchoolNotices();

}
