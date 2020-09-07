package org.graduationdesign.gdmsauth.daoImpl;

import org.graduationdesign.gdmsauth.dao.UserDao;
import org.graduationdesign.gdmsauth.entity.User;
import org.graduationdesign.gdmsauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> find(String id){
        return userRepository.findDistinctById(id);
    }
}
