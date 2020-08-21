package org.graduationdesign.gdms_notice.repository;

import org.graduationdesign.gdms_notice.entity.SchoolNoticeContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface SchoolNoticeContentRepository extends MongoRepository<SchoolNoticeContent,Integer> {
    SchoolNoticeContent findById(@Param("_id")int id);
}
