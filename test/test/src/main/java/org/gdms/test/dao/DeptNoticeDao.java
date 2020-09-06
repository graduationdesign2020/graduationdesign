package org.gdms.test.dao;

import org.gdms.test.entity.DeptNotice;

import java.util.List;

public interface DeptNoticeDao {
    List<DeptNotice> getDeptNoticesByDept(String department);

    DeptNotice getDeptNoticeById(int id);

    List<DeptNotice> getThreeDeptNoticesByDepartment(String dept);
}
