package org.graduationdesign.gdmsservernotice.serviceimpl;

import org.graduationdesign.gdmsservernotice.dao.DeptNoticeDao;
import org.graduationdesign.gdmsservernotice.dao.SchoolNoticeDao;
import org.graduationdesign.gdmsservernotice.dao.StudentDao;
import org.graduationdesign.gdmsservernotice.dao.TeacherDao;
import org.graduationdesign.gdmsservernotice.entity.DeptNotice;
import org.graduationdesign.gdmsservernotice.entity.SchoolNotice;
import org.graduationdesign.gdmsservernotice.service.NoticeService;
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
