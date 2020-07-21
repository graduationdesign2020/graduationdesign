package com.example.demo.serviceimpl;

import com.example.demo.DemoApplicationTests;
import com.example.demo.dao.DeptNoticeDao;
import com.example.demo.dao.SchoolNoticeDao;
import com.example.demo.entity.DeptNotice;
import com.example.demo.entity.SchoolNotice;
import com.example.demo.service.NoticeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Rollback
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class NoticeServiceTest extends DemoApplicationTests {
    @Autowired
    private DeptNoticeDao deptNoticeDao;
    @Autowired
    private SchoolNoticeDao schoolNoticeDao;
    @Autowired
    private NoticeService noticeService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getDeptNoticesByDept() {
        List<DeptNotice> result = deptNoticeDao.getDeptNoticesByDept("电院");
        List<DeptNotice> compare = new ArrayList<>();
//        DeptNotice deptNotice1 = new DeptNotice();
//        DeptNotice deptNotice2 = new DeptNotice();
//        DeptNotice deptNotice3 = new DeptNotice();
//        DeptNotice deptNotice4 = new DeptNotice();
//        DeptNotice deptNotice5 = new DeptNotice();
//        deptNotice3.init(3, "3", "SE", "2020-07-17 13:50:04");
//        deptNotice1.init(1, "1", "SE", "2020-07-16 13:49:55");
//        deptNotice5.init(5, "5", "SE", "2020-07-15 13:50:06");
//        deptNotice2.init(2, "2", "CS", "2020-07-17 13:50:01");
//        deptNotice4.init(4, "4", "SE", "2020-07-14 13:50:11");
//        compare.add(deptNotice3);
//        compare.add(deptNotice1);
//        compare.add(deptNotice5);
//        compare.add(deptNotice4);
        //compare.add(deptNotice2);
        compare = noticeService.getDeptNoticesByDept("电院");

        assertEquals(result, compare);
    }

    @Test
    public void getDeptNoticesById() {
        DeptNotice result = deptNoticeDao.getDeptNoticeById(28);
        DeptNotice compare = noticeService.getDeptNoticeById(28);//new DeptNotice();
//        compare.init(1, "1", "SE", "2020-07-16 13:49:55");
//        compare.setContent("department notice content1");

        assertEquals(result, compare);
    }

    @Test
    public void getSchoolNoticeById() {
        SchoolNotice result = schoolNoticeDao.getSchoolNoticeById(7);
        SchoolNotice compare = noticeService.getSchoolNoticeById(7);//new SchoolNotice();
//        compare.init(1, "1", "2020-07-16 13:50:57");
//        compare.setContent("school notice content1");

        assertEquals(result, compare);
    }

    @Test
    public void getSchoolNotices() {
        List<SchoolNotice> result = schoolNoticeDao.getSchoolNotices();
        List<SchoolNotice> compare = noticeService.getSchoolNotices();//new ArrayList<>();
//        SchoolNotice schoolNotice1 = new SchoolNotice(),
//                schoolNotice2 = new SchoolNotice(),
//                schoolNotice3 = new SchoolNotice(),
//                schoolNotice4 = new SchoolNotice();
//
//        schoolNotice2.init(2, "2", "2020-07-17 13:51:04");
//        schoolNotice1.init(1, "1", "2020-07-16 13:50:57");
//        schoolNotice4.init(4, "4", "2020-07-15 13:51:07");
//        schoolNotice3.init(3, "3", "2020-07-14 13:51:11");
//        compare.add(schoolNotice2);
//        compare.add(schoolNotice1);
//        compare.add(schoolNotice4);
//        compare.add(schoolNotice3);

        assertEquals(result, compare);
    }

    @Test
    public void getThreeSchoolNotices() {
        List<SchoolNotice> result = schoolNoticeDao.getThreeSchoolNotices();
        List<SchoolNotice> compare = noticeService.getThreeSchoolNotices();//new ArrayList<>();
//        SchoolNotice schoolNotice1 = new SchoolNotice(), schoolNotice2 = new SchoolNotice(), schoolNotice3 = new SchoolNotice();
//        schoolNotice1.init(2, "2", "2020-07-17 13:51:04");
//        schoolNotice2.init(1, "1", "2020-07-16 13:50:57");
//        schoolNotice3.init(4, "4", "2020-07-15 13:51:07");
//        compare.add(schoolNotice1);
//        compare.add(schoolNotice2);
//        compare.add(schoolNotice3);

        assertEquals(result, compare);
    }

    @Test
    public void getThreeDeptNoticesByDepartment() {
        List<DeptNotice> result = deptNoticeDao.getThreeDeptNoticesByDepartment("电院");
        List<DeptNotice> compare = noticeService.getThreeDeptNoticesByDepartment("电院");//new ArrayList<>();
//        DeptNotice deptNotice1 = new DeptNotice();
//        DeptNotice deptNotice2 = new DeptNotice();
//        DeptNotice deptNotice3 = new DeptNotice();
//        deptNotice1.init(3, "3", "SE", "2020-07-17 13:50:04");
//        deptNotice2.init(1, "1", "SE", "2020-07-16 13:49:55");
//        deptNotice3.init(5, "5", "SE", "2020-07-15 13:50:06");
//        compare.add(deptNotice1);
//        compare.add(deptNotice2);
//        compare.add(deptNotice3);

        assertEquals(result, compare);
    }
}
