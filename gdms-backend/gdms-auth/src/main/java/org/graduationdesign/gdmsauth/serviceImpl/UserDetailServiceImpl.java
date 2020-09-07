package org.graduationdesign.gdmsauth.serviceImpl;

import lombok.SneakyThrows;
import org.graduationdesign.gdmsauth.dao.UserDao;
import org.graduationdesign.gdmsauth.entity.JwtUser;
import org.graduationdesign.gdmsauth.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userdao;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<User> user = userdao.find(id);
        if(!user.isPresent()){
            throw new UsernameNotFoundException("not exist user");
        }

        return  new JwtUser(user.get());
    }
}
