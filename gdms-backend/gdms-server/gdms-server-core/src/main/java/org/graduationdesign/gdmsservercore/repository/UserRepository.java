package org.graduationdesign.gdmsservercore.repository;

import org.graduationdesign.gdmsservercore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    @Query("from User where wechat=:id")
    Optional<User> getByWechat(String id);

    @Query("from User where id=:id and auth=:auth")
    Optional<User> getByIdAndAuth(String id,String auth);
    @Query(value = "delete from User where id=:id")
    @Modifying
    @Transactional
    int deleteByid(String id);

    Optional<User> findDistinctById(String id);
}
