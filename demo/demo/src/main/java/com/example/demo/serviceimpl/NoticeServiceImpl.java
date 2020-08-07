package com.example.demo.serviceimpl;

import com.example.demo.dao.DeptNoticeDao;
import com.example.demo.dao.SchoolNoticeDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.dao.TeacherDao;
import com.example.demo.entity.DeptNotice;
import com.example.demo.entity.DeptNoticeContent;
import com.example.demo.entity.SchoolNotice;
import com.example.demo.entity.SchoolNoticeContent;
import com.example.demo.repository.DeptNoticeContentRepository;
import com.example.demo.repository.SchoolNoticeContentRepository;
import com.example.demo.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
