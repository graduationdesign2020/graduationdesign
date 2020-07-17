package com.example.demo.service;

import com.example.demo.utils.UserInfo;

public interface LoginService {
    String register(String wechat_id,String id,String name,int teacher);
    String logout(String wechat_id);
    UserInfo login(String wechat_id);
}

