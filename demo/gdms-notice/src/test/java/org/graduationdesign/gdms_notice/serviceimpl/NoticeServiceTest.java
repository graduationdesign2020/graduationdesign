package org.graduationdesign.gdms_notice.serviceimpl;

import org.graduationdesign.gdms_notice.GdmsNoticeApplicationTests;
import org.graduationdesign.gdms_notice.dao.DeptNoticeDao;
import org.graduationdesign.gdms_notice.dao.SchoolNoticeDao;
import org.graduationdesign.gdms_notice.entity.DeptNotice;
import org.graduationdesign.gdms_notice.entity.SchoolNotice;
import org.graduationdesign.gdms_notice.service.NoticeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Rollback
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class NoticeServiceTest extends GdmsNoticeApplicationTests {
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
        List<DeptNotice> result = deptNoticeDao.getDeptNoticesByDept("电子信息与电气工程学院");
        List<DeptNotice> compare = noticeService.getDeptNoticesByDept("电子信息与电气工程学院");

        assertEquals(result, compare);
    }

    @Test
    public void getDeptNoticesById() {
        DeptNotice result = deptNoticeDao.getDeptNoticeById(156);
        DeptNotice compare = noticeService.getDeptNoticeById(156);

        assertEquals(result, compare);
    }

    @Test
    public void getSchoolNoticeById() {
        SchoolNotice result = schoolNoticeDao.getSchoolNoticeById(23);
        SchoolNotice compare = noticeService.getSchoolNoticeById(23);

        assertEquals(result, compare);
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
    public void getThreeDeptNoticesBySid() {
        List<DeptNotice> compare = noticeService.getThreeDeptNoticesBySid("816249335790");
        assertEquals(3, compare.size());
    }

    @Test
    public void getThreeDeptNoticesByTid() {
       List<DeptNotice> compare = noticeService.getThreeDeptNoticesByTid("60396j");
        assertEquals(3, compare.size());
    }

    @Test
    public void getDeptNoticesBySid() {
        List<DeptNotice> compare = noticeService.getDeptNoticesBySid("816249335790");
        assertEquals(30, compare.size());
    }

    @Test
    public void getDeptNoticesByTid() {
        List<DeptNotice> compare = noticeService.getDeptNoticesByTid("60396j");
        assertEquals(30, compare.size());
    }

}
