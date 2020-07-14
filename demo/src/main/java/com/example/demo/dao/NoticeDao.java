package com.example.demo.dao;

import com.example.demo.entity.Notice;

import java.util.List;

public interface NoticeDao {
    Notice getNoticeById(int id);

    List<Notice> getNotices();

    int addReads(int id);//将notice表中reads改成reads+1
}
