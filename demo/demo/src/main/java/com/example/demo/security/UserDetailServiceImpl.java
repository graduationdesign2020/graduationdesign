package com.example.demo.security;

import com.example.demo.constant.appId;
import com.example.demo.dao.UsersDao;
import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;
import com.example.demo.utils.CodeReturn;
import com.example.demo.utils.HttpClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersDao usersDao;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String wechat_id) throws UsernameNotFoundException {
        Optional<Users> user = usersDao.getUserByWechat_id(wechat_id);
        System.err.println(wechat_id);
        System.err.println(user.isPresent());

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Not Found.");
        }
        System.out.println(user.get());
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.get().getAuth());
        System.err.println("username is " + wechat_id + ", "+ user.get().getId() + ", " + user.get().getAuth());
        User u = new User(user.get().getWechat_id(), new BCryptPasswordEncoder().encode(user.get().getId()), authorities);
        System.out.println(u);
        return u;
    }
}
