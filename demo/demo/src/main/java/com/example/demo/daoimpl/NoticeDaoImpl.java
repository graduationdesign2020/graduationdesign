package com.example.demo.daoimpl;

import com.example.demo.dao.NoticeDao;
import com.example.demo.entity.Notice;
import com.example.demo.entity.NoticeContent;
import com.example.demo.repository.NoticeContentRepository;
import com.example.demo.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeDaoImpl implements NoticeDao {
    @Autowired
    NoticeRepository noticeRepository;
    @Autowired
    NoticeContentRepository noticeContentRepository;

    @Override
    public Notice getNoticeById(int id){
        Notice notice=noticeRepository.getOne(id);
        NoticeContent noticeContent=noticeContentRepository.findByNoticeId(id);
        notice.setContent(noticeContent.getContent());
        return notice;
    }

    @Override
    public List<Notice> getNotices(){
        return noticeRepository.getNotices();
    }
    @Override
    public int addReads(int id){
        return noticeRepository.addReads(id);
    }

}
