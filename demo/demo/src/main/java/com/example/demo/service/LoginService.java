package com.example.demo.service;

import com.example.demo.utils.ReturnInfo;

public interface LoginService {
    ReturnInfo register(String wechat_id,String id,String name,int teacher);
    ReturnInfo logout(String wechat_id);
    ReturnInfo login(String wechat_id);
}
