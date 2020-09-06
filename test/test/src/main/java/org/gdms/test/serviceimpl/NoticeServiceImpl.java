package org.gdms.test.serviceimpl;

import org.gdms.test.dao.DeptNoticeDao;
import org.gdms.test.dao.SchoolNoticeDao;
import org.gdms.test.dao.StudentDao;
import org.gdms.test.dao.TeacherDao;
import org.gdms.test.entity.DeptNotice;
import org.gdms.test.entity.SchoolNotice;
import org.gdms.test.service.NoticeService;
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
