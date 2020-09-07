package org.graduationdesign.gdmsauth.repository;

import org.graduationdesign.gdmsauth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findDistinctById(String id);
}
