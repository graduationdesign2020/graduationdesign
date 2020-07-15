package com.example.demo.repository;

import com.example.demo.entity.DeptNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeptNoticeRepository extends JpaRepository<DeptNotice,Integer>{
    @Query("from DeptNotice where department=:dept")
    List<DeptNotice> getDeptNoticesByDepartment(String dept);
}