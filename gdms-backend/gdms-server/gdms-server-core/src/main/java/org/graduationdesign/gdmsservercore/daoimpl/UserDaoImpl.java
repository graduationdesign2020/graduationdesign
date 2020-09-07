package org.graduationdesign.gdmsservercore.daoimpl;

import org.graduationdesign.gdmsservercore.dao.UserDao;
import org.graduationdesign.gdmsservercore.entity.User;
import org.graduationdesign.gdmsservercore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private UserRepository usersRepository;


    @Override
    public Optional<User> getUserByWechat(String wechat_id){
        return usersRepository.getByWechat(wechat_id);
    }

    @Override
    public User saveUsers(User users){
        return usersRepository.save(users);
    }

    @Override
    public int deleteUsers(String id){
        int check=usersRepository.deleteByid(id);
        return check;
    }

    @Override
    public Optional<User> getByIdAndAuth(String id,String auth){
        return usersRepository.getByIdAndAuth(id, auth);
    }

    @Override
    public Optional<User> getById(String id){
        return usersRepository.findDistinctById(id);
    }
}
