package com.example.demo.repository;

import com.example.demo.entity.SysMessage;
import com.example.demo.entity.TeacherMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SysMessageRepository extends JpaRepository<SysMessage,Integer> {
    @Query("from SysMessage where student_id=:stu_id order by time desc")
    List<SysMessage> getSysMessagesByStudent_id(String stu_id);


    @Transactional
    @Modifying
    @Query(value = "update sysmessage set is_read=1 where id=?",nativeQuery=true)
    int setRead(int id);
}