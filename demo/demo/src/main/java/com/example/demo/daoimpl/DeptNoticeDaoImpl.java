package com.example.demo.daoimpl;

import com.example.demo.dao.DeptNoticeDao;
import com.example.demo.entity.DeptNotice;
import com.example.demo.entity.DeptNoticeContent;
import com.example.demo.repository.DeptNoticeContentRepository;
import com.example.demo.repository.DeptNoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeptNoticeDaoImpl implements DeptNoticeDao {
    @Autowired
    private DeptNoticeRepository deptNoticeRepository;
    @Autowired
    private DeptNoticeContentRepository deptNoticeContentRepository;

    @Override
    public List<DeptNotice> getDeptNoticesByDept(String department){
        return deptNoticeRepository.getDeptNoticesByDepartment(department);
    }

    @Override
    public DeptNotice getDeptNoticeById(int id){
        DeptNotice deptNotice=deptNoticeRepository.getOne(id);
        DeptNoticeContent deptNoticeContent=deptNoticeContentRepository.findById(id);
        deptNotice.setContent(deptNoticeContent.getContent());
        return deptNotice;
    }

    @Override
    public List<DeptNotice> getThreeDeptNoticesByDepartment(String dept){
        return deptNoticeRepository.getThreeDeptNoticesByDepartment(dept);
    }



}
