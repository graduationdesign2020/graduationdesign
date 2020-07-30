package com.example.demo.service;

import com.example.demo.utils.ReturnInfo;
import com.example.demo.utils.UserInfo;

public interface LoginService {
    ReturnInfo register(String wechat_id,String id,String name,String auth);
    ReturnInfo logout(String wechat_id);
    UserInfo getUserData(String id, String role);
}

