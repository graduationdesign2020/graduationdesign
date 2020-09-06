package org.graduationdesign.gdmsservercore.repository;

import org.graduationdesign.gdmsservercore.entity.ReplyMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ReplyMessageRepository extends MongoRepository<ReplyMessage,Integer> {
    Optional<ReplyMessage> findById(@Param("_id")int id);

}
