package org.graduationdesign.gdmsauth.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

public class JwtUser implements UserDetails {
    private String id;
    private String wechat;
    private Boolean enabled;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser() {
    }

    /**
     * 通过 user 对象创建jwtUser
     */
    public JwtUser(User user) {
        id = user.getId();
        wechat = user.getWechat();
        enabled = user.isEnabled();
        password = user.getPassword();
        List<GrantedAuthority> authority = AuthorityUtils.createAuthorityList(user.getAuth());
        authorities = authority;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public String toString() {
        return "JwtUser{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", wechat='" + wechat + '\'' +
                ", authorities=" + authorities +
                '}';
    }

}
