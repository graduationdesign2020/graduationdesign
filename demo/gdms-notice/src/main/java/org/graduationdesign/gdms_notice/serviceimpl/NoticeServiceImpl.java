package org.graduationdesign.gdms_notice.serviceimpl;

import org.graduationdesign.gdms_notice.dao.DeptNoticeDao;
import org.graduationdesign.gdms_notice.dao.SchoolNoticeDao;
import org.graduationdesign.gdms_notice.dao.StudentDao;
import org.graduationdesign.gdms_notice.dao.TeacherDao;
import org.graduationdesign.gdms_notice.entity.DeptNotice;
import org.graduationdesign.gdms_notice.entity.SchoolNotice;
import org.graduationdesign.gdms_notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private DeptNoticeDao deptNoticeDao;
    @Autowired
    private SchoolNoticeDao schoolNoticeDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public List<DeptNotice> getDeptNoticesByDept(String department){
        return deptNoticeDao.getDeptNoticesByDept(department);
    }

    @Override
    public DeptNotice getDeptNoticeById(int id){
         return deptNoticeDao.getDeptNoticeById(id);
    }

    @Override
    public SchoolNotice getSchoolNoticeById(int id)
    {
        return schoolNoticeDao.getSchoolNoticeById(id);
    }

    @Override
    public List<SchoolNotice> getSchoolNotices()
    {
        return schoolNoticeDao.getSchoolNotices();
    }

    @Override
    public List<SchoolNotice> getThreeSchoolNotices()
    {
        return schoolNoticeDao.getThreeSchoolNotices();
    }


    @Override
    public List<DeptNotice> getThreeDeptNoticesBySid(String id)
    {
        String dept = studentDao.getDeptById(id);
        return deptNoticeDao.getThreeDeptNoticesByDepartment(dept);
    }

    @Override
    public List<DeptNotice> getDeptNoticesBySid(String id){
        String dept = studentDao.getDeptById(id);
        return deptNoticeDao.getDeptNoticesByDept(dept);
    }

    @Override
    public List<DeptNotice> getDeptNoticesByTid(String id){
        String dept = teacherDao.getDeptById(id);
        return deptNoticeDao.getDeptNoticesByDept(dept);
    }

    @Override
    public List<DeptNotice> getThreeDeptNoticesByTid(String id)
    {
        String dept = teacherDao.getDeptById(id);
        return deptNoticeDao.getThreeDeptNoticesByDepartment(dept);
    }
}
