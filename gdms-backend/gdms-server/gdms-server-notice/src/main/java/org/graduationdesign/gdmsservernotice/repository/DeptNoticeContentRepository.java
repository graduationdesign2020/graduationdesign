package org.graduationdesign.gdmsservernotice.repository;

import org.graduationdesign.gdmsservernotice.entity.DeptNoticeContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface DeptNoticeContentRepository extends MongoRepository<DeptNoticeContent,Integer> {
    DeptNoticeContent findById(@Param("_id")int id);
}
