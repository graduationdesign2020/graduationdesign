package org.graduationdesign.gdmsservernotice.daoimpl;

import org.graduationdesign.gdmsservernotice.dao.DeptNoticeDao;
import org.graduationdesign.gdmsservernotice.entity.DeptNotice;
import org.graduationdesign.gdmsservernotice.entity.DeptNoticeContent;
import org.graduationdesign.gdmsservernotice.repository.DeptNoticeContentRepository;
import org.graduationdesign.gdmsservernotice.repository.DeptNoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
        Optional<DeptNotice> schoolNotice= deptNoticeRepository.getById(id);
        if(schoolNotice.isPresent()) {
            DeptNotice schoolNotice1=schoolNotice.get();
            Optional<DeptNoticeContent> schoolNoticeContent = Optional.ofNullable(deptNoticeContentRepository.findById(id*10));
            if (schoolNoticeContent.isPresent()) {
                DeptNoticeContent s = schoolNoticeContent.get();
                schoolNotice1.setContent(s.getContent());
            } else {
                schoolNotice1.setContent(null);
            }
            return schoolNotice1;
        }
        else return null;

    }

    @Override
    public List<DeptNotice> getThreeDeptNoticesByDepartment(String dept){
        return deptNoticeRepository.getThreeDeptNoticesByDepartment(dept);
    }



}
