package com.example.demo.serviceimpl;

import com.example.demo.dao.DeptNoticeDao;
import com.example.demo.dao.SchoolNoticeDao;
import com.example.demo.entity.DeptNotice;
import com.example.demo.entity.SchoolNotice;
import com.example.demo.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
