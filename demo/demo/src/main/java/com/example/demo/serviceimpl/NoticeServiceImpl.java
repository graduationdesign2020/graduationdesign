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
    @Autowired
    private DeptNoticeContentRepository deptNoticeContentRepository;
    @Autowired
    private SchoolNoticeContentRepository schoolNoticeContentRepository;

    @Override
    public List<DeptNotice> getDeptNoticesByDept(String department){
        return deptNoticeDao.getDeptNoticesByDept(department);
    }

    @Override
    public DeptNotice getDeptNoticeById(int id){
        Optional<DeptNotice> deptNotice= deptNoticeDao.getDeptNoticeById(id);
        if(deptNotice.isPresent()) {
            DeptNotice schoolNotice1=deptNotice.get();
            Optional<DeptNoticeContent> schoolNoticeContent = Optional.ofNullable(deptNoticeContentRepository.findById(id));
            if (schoolNoticeContent.isPresent()) {
                DeptNoticeContent s = schoolNoticeContent.get();
                schoolNotice1.setContent(s.getContent());
            } else {
                schoolNotice1.setContent(null);
            }
            return schoolNotice1;
        }
        else {
            DeptNotice schoolNotice2=new DeptNotice();
            return schoolNotice2;
        }
    }

    @Override
    public SchoolNotice getSchoolNoticeById(int id)
    {
        Optional<SchoolNotice> schoolNotice= schoolNoticeDao.getSchoolNoticeById(id);
        if(schoolNotice.isPresent()) {
            SchoolNotice schoolNotice1=schoolNotice.get();
            Optional<SchoolNoticeContent> schoolNoticeContent = Optional.ofNullable(schoolNoticeContentRepository.findById(id));
            if (schoolNoticeContent.isPresent()) {
                SchoolNoticeContent s = schoolNoticeContent.get();
                schoolNotice1.setContent(s.getContent());
            } else {
                schoolNotice1.setContent(null);
            }
            return schoolNotice1;
        }
        else {
            SchoolNotice schoolNotice2=new SchoolNotice();
            return schoolNotice2;
        }
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
