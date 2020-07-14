package com.example.demo.repository;

import com.example.demo.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice,Integer> {
    @Query("from Notice")
    List<Notice> getNotices();

    @Transactional
    @Modifying
    @Query(value = "update notice set read_number=read_number+1 where id=?",nativeQuery = true)
    int addReads(int id);

}
