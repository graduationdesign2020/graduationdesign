package org.graduationdesign.gdmsauth.dao;

import org.graduationdesign.gdmsauth.entity.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> find(String id);
}
