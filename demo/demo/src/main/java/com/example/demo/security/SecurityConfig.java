package com.example.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailService)
            .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .httpBasic()
                    .authenticationEntryPoint((request,response,authException) -> {
                        response.setContentType("application/json;charset=utf-8");
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        PrintWriter out = response.getWriter();
                        Map<String,Object> map = new HashMap<String,Object>();
                        map.put("code",403);
                        map.put("msg","未登录");
                        out.write(objectMapper.writeValueAsString(map));
                        out.flush();
                        out.close();
                    })
                    .and()
                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers("/","/login", "/mylogin", "/register", "/mylogout").permitAll()
                    .anyRequest().authenticated()
                    .and()

                .formLogin()
                    .loginProcessingUrl("/login")
                    .permitAll()
                //.exceptionHandling()
                //没有权限，返回json
//                .accessDeniedHandler((request,response,ex) -> {
//                    response.setContentType("application/json;charset=utf-8");
//                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                    PrintWriter out = response.getWriter();
//                    Map<String,Object> map = new HashMap<String,Object>();
//                    map.put("code",403);
//                    map.put("msg", "权限不足");
//                    out.write(objectMapper.writeValueAsString(map));
//                    out.flush();
//                    out.close();
//                })
                .and()
                .logout()
                    .logoutUrl("/logout");
    }

    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler((request,response,authentication) -> {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("code",200);
            map.put("msg","登录成功");
            map.put("data",authentication);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(objectMapper.writeValueAsString(map));
            out.flush();
            out.close();
        });
        filter.setAuthenticationFailureHandler((request,response,ex) -> {
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter out = response.getWriter();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("code",401);
            if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
                map.put("msg","用户名或密码错误");
            } else if (ex instanceof DisabledException) {
                map.put("msg","账户被禁用");
            } else {
                map.put("msg","登录失败!");
            }
            out.write(objectMapper.writeValueAsString(map));
            out.flush();
            out.close();
        });
        filter.setAuthenticationManager(authenticationManager());
        System.out.println(filter.getAuthenticationManager() == null);
        return filter;
    }
}
