package org.gdms.test.repository;

import org.gdms.test.entity.TeacherMessageContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TeacherMessageContentRepository extends MongoRepository<TeacherMessageContent,Integer> {
    Optional<TeacherMessageContent> findById(@Param("_id")int id);

}



