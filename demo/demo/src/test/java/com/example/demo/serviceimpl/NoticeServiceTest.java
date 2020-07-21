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
import java.util.Optional;

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
        List<DeptNotice> compare = noticeService.getDeptNoticesByDept("电院");

        assertEquals(result, compare);
    }

    @Test
    public void getDeptNoticesById() {
        Optional<DeptNotice> result = deptNoticeDao.getDeptNoticeById(28);
        DeptNotice compare = noticeService.getDeptNoticeById(28);

        assertEquals(result.get(), compare);
    }

    @Test
    public void getSchoolNoticeById() {
        Optional<SchoolNotice> result = schoolNoticeDao.getSchoolNoticeById(7);
        SchoolNotice compare = noticeService.getSchoolNoticeById(7);

        assertEquals(result.get(), compare);
    }

    @Test
    public void getSchoolNotices() {
        List<SchoolNotice> result = schoolNoticeDao.getSchoolNotices();
        List<SchoolNotice> compare = noticeService.getSchoolNotices();

        assertEquals(result, compare);
    }

    @Test
    public void getThreeSchoolNotices() {
        List<SchoolNotice> result = schoolNoticeDao.getThreeSchoolNotices();
        List<SchoolNotice> compare = noticeService.getThreeSchoolNotices();

        assertEquals(result, compare);
    }

    @Test
    public void getThreeDeptNoticesByDepartment() {
        List<DeptNotice> result = deptNoticeDao.getThreeDeptNoticesByDepartment("电院");
        List<DeptNotice> compare = noticeService.getThreeDeptNoticesByDepartment("电院");

        assertEquals(result, compare);
    }
}
