package com.example.demo.daoimpl;

import com.example.demo.dao.SchoolNoticeDao;
import com.example.demo.entity.SchoolNotice;
import com.example.demo.entity.SchoolNoticeContent;
import com.example.demo.repository.SchoolNoticeContentRepository;
import com.example.demo.repository.SchoolNoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SchoolNoticeDaolmpl implements SchoolNoticeDao {
    @Autowired
    private SchoolNoticeRepository schoolNoticeRepository;
    @Autowired
    private SchoolNoticeContentRepository schoolNoticeContentRepository;

    @Override
    public List<SchoolNotice> getSchoolNotices(){
        return schoolNoticeRepository.getSchoolNotices();
    }

    @Override
    public SchoolNotice getSchoolNoticeById(int id){
        SchoolNotice schoolNotice= schoolNoticeRepository.getOne(id);
        SchoolNoticeContent schoolNoticeContent=schoolNoticeContentRepository.findById(id);
        schoolNotice.setContent(schoolNoticeContent.getContent());
        return schoolNotice;
    }
}
