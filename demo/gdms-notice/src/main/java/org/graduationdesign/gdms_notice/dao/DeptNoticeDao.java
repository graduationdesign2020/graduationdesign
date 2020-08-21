package org.graduationdesign.gdms_notice.dao;

import org.graduationdesign.gdms_notice.entity.DeptNotice;

import java.util.List;

public interface DeptNoticeDao {
    List<DeptNotice> getDeptNoticesByDept(String department);

    DeptNotice getDeptNoticeById(int id);

    List<DeptNotice> getThreeDeptNoticesByDepartment(String dept);
}
