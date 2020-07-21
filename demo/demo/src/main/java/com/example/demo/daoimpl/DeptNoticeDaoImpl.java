package com.example.demo.daoimpl;

import com.example.demo.dao.DeptNoticeDao;
import com.example.demo.entity.DeptNotice;
import com.example.demo.entity.DeptNoticeContent;
import com.example.demo.entity.SchoolNotice;
import com.example.demo.entity.SchoolNoticeContent;
import com.example.demo.repository.DeptNoticeContentRepository;
import com.example.demo.repository.DeptNoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DeptNoticeDaoImpl implements DeptNoticeDao {
    @Autowired
    private DeptNoticeRepository deptNoticeRepository;

    @Override
    public List<DeptNotice> getDeptNoticesByDept(String department){
        return deptNoticeRepository.getDeptNoticesByDepartment(department);
    }

    @Override
    public Optional<DeptNotice> getDeptNoticeById(int id){
        return deptNoticeRepository.getById(id);

    }

    @Override
    public List<DeptNotice> getThreeDeptNoticesByDepartment(String dept){
        return deptNoticeRepository.getThreeDeptNoticesByDepartment(dept);
    }



}
