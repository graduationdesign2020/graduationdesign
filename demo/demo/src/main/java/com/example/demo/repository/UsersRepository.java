package com.example.demo.repository;

import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,String> {
    @Query("from Users where wechat_id=:id")
    Optional<Users> getByWechat_id(String id);

    @Query("from Users where id=:id and auth=:auth")
    Optional<Users> getByIdAndAuth(String id,Integer auth);
    @Query(value = "delete from Users where wechat_id=:wechat_id")
    @Modifying
    @Transactional
    int deleteByWechat_id(String wechat_id);
}
