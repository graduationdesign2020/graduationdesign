package org.gdms.test.daoimpl;

import org.gdms.test.dao.UsersDao;
import org.gdms.test.entity.Users;
import org.gdms.test.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsersDaoImpl implements UsersDao {
    @Autowired
    private UsersRepository usersRepository;


    @Override
    public Optional<Users> getUserByWechat_id(String wechat_id){
        return usersRepository.getByWechat_id(wechat_id);
    }

    @Override
    public Users saveUsers(Users users){
        return usersRepository.save(users);
    }

    @Override
    public int deleteUsers(String id){
        int check=usersRepository.deleteByid(id);
        return check;
    }

    @Override
    public Optional<Users> getByIdAndAuth(String id,String auth){
        return usersRepository.getByIdAndAuth(id, auth);
    }

    @Override
    public Optional<Users> getById(String id){
        return usersRepository.findDistinctById(id);
    }
}
