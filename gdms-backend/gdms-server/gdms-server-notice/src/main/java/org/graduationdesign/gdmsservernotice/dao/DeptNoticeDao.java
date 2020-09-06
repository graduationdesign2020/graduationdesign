package org.graduationdesign.gdmsservernotice.dao;

import org.graduationdesign.gdmsservernotice.entity.DeptNotice;

import java.util.List;

public interface DeptNoticeDao {
    List<DeptNotice> getDeptNoticesByDept(String department);

    DeptNotice getDeptNoticeById(int id);

    List<DeptNotice> getThreeDeptNoticesByDepartment(String dept);
}
