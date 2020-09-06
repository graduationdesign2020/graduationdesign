package org.graduationdesign.gdmsservernotice.daoimpl;

import org.graduationdesign.gdmsservernotice.dao.SchoolNoticeDao;
import org.graduationdesign.gdmsservernotice.entity.SchoolNotice;
import org.graduationdesign.gdmsservernotice.entity.SchoolNoticeContent;
import org.graduationdesign.gdmsservernotice.repository.SchoolNoticeContentRepository;
import org.graduationdesign.gdmsservernotice.repository.SchoolNoticeRepository;
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
        Optional<SchoolNotice> schoolNotice= schoolNoticeRepository.getById(id);
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
        else return null;
    }

    @Override
    public List<SchoolNotice> getThreeSchoolNotices(){
        return schoolNoticeRepository.getThreeSchoolNotices();
    }
}
