package org.graduatiedesign.gdmsservercore.repository;

import org.graduatiedesign.gdmsservercore.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,String> {
    @Query("from Users where wechat_id=:id")
    Optional<Users> getByWechat_id(String id);

    @Query("from Users where id=:id and auth=:auth")
    Optional<Users> getByIdAndAuth(String id,String auth);
    @Query(value = "delete from Users where id=:id")
    @Modifying
    @Transactional
    int deleteByid(String id);

    Optional<Users> findDistinctById(String id);
}
