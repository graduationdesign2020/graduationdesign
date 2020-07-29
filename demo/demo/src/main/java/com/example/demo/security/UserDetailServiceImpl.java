package com.example.demo.security;

import com.example.demo.dao.UsersDao;
import com.example.demo.entity.Users;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UsersDao usersDao;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<Users> user = usersDao.getById(id);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Not Found.");
        }
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.get().getAuth());
        User u = new User(user.get().getId(), new BCryptPasswordEncoder().encode(user.get().getWechat_id()), authorities);
        return u;
    }
}
