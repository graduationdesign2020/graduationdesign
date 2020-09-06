package org.gdms.test.service;

import org.gdms.test.entity.DeptNotice;
import org.gdms.test.entity.SchoolNotice;

import java.util.List;

public interface NoticeService {
    List<DeptNotice> getDeptNoticesByDept(String department);

    List<DeptNotice> getDeptNoticesBySid(String id);

    List<DeptNotice> getDeptNoticesByTid(String id);

    DeptNotice getDeptNoticeById(int id);

    SchoolNotice getSchoolNoticeById(int id);

    List<SchoolNotice> getSchoolNotices();

    List<SchoolNotice> getThreeSchoolNotices();

    List<DeptNotice> getThreeDeptNoticesBySid(String id);

    List<DeptNotice> getThreeDeptNoticesByTid(String id);
}
