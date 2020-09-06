package org.gdms.test.repository;

import org.gdms.test.entity.DeptNoticeContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface DeptNoticeContentRepository extends MongoRepository<DeptNoticeContent,Integer> {
    DeptNoticeContent findById(@Param("_id")int id);
}
