package com.example.demo.dao;

import com.example.demo.entity.SysMessage;

import java.util.List;

public interface SysMessageDao {
    SysMessage getSysMessageById(int id);

    List<SysMessage> getSysMessages(String stu_id);

    void sentSysMessage(SysMessage sysMessage);//将(title,content,teacherId)插入到message表

    int setRead(int id);//将is_read改成1
}
