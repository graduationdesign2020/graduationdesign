package org.graduationdesign.gdmsservercore.dao;

import org.graduationdesign.gdmsservercore.entity.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> getUserByWechat(String wechat_id);

    User saveUsers(User users);

    int deleteUsers(String wechat_id);

    Optional<User> getByIdAndAuth(String id,String auth);

    Optional<User> getById(String id);
}
