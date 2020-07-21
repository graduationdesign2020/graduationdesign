package com.example.demo.serviceimpl;

import com.example.demo.dao.DeptNoticeDao;
import com.example.demo.dao.SchoolNoticeDao;
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
    public List<DeptNotice> getThreeDeptNoticesByDepartment(String dept)
    {
        return deptNoticeDao.getThreeDeptNoticesByDepartment(dept);
    }
}
