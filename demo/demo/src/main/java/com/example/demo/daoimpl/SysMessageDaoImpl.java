package com.example.demo.daoimpl;

import com.example.demo.dao.SysMessageDao;
import com.example.demo.entity.SysMessage;
import com.example.demo.repository.SysMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysMessageDaoImpl implements SysMessageDao {
    @Autowired
    private SysMessageRepository sysMessageRepository;

    @Override
    public SysMessage getSysMessageById(int id){
        return sysMessageRepository.getOne(id);
    }

    @Override
    public List<SysMessage> getSysMessages(String stu_id){
        return sysMessageRepository.getSysMessagesByStudent_id(stu_id);
    }

    @Override
    public void sentSysMessage(SysMessage sysMessage){
        sysMessageRepository.save(sysMessage);
    }

    @Override
    public int setRead(int id){
        return sysMessageRepository.setRead(id);
    }
}
