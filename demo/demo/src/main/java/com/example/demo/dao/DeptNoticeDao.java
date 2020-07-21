package com.example.demo.dao;

import com.example.demo.entity.DeptNotice;

import java.util.List;
import java.util.Optional;

public interface DeptNoticeDao {
    DeptNotice getDeptNoticesByDept(String department);

    Optional<DeptNotice> getDeptNoticeById(int id);

    List<DeptNotice> getThreeDeptNoticesByDepartment(String dept);
}
