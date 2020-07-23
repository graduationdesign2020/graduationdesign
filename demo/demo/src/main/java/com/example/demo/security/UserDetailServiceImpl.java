package com.example.demo.security;

import com.example.demo.dao.UsersDao;
import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = usersDao.getUserByWechat_id(username);//usersRepository.getByWechat_id(username);
        System.err.println(username);
        System.err.println(user.isPresent());

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Not Found.");
        }
        System.out.println(user.get());
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.get().getAuth());
        //List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //authorities.add(new SimpleGrantedAuthority(String.valueOf(user.get().getAuth())));
        System.err.println("username is " + username + ", "+ user.get().getId() + ", " + user.get().getAuth());
        User u = new User(user.get().getWechat_id(), new BCryptPasswordEncoder().encode(user.get().getId()), authorities);
        System.out.println(u);
        return u;
    }
}
