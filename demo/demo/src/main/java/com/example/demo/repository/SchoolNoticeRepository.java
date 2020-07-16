package com.example.demo.repository;

import com.example.demo.entity.DeptNotice;
import com.example.demo.entity.SchoolNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchoolNoticeRepository extends JpaRepository<SchoolNotice,Integer> {
    @Query("from SchoolNotice ")
    List<SchoolNotice> getSchoolNotices();

    @Query(value="select * from schoolnotice order by time desc limit 3",nativeQuery=true)
    List<SchoolNotice> getThreeSchoolNotices();
}
