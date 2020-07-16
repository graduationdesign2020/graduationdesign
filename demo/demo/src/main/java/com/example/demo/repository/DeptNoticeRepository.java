package com.example.demo.repository;

import com.example.demo.entity.DeptNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DeptNoticeRepository extends JpaRepository<DeptNotice,Integer>{
    @Query("from DeptNotice where department=:dept order by time desc")
    List<DeptNotice> getDeptNoticesByDepartment(String dept);

    @Query(value="select * from deptnotice where department=? order by time desc limit 3",nativeQuery=true)
    List<DeptNotice> getThreeDeptNoticesByDepartment(String dept);
}