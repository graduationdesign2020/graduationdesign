package com.example.demo.controller;

import com.example.demo.entity.TeacherMessage;
import com.example.demo.service.LoginService;
import com.example.demo.utils.ReturnInfo;
import com.example.demo.utils.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping(path = "/register")
    public ReturnInfo register(@RequestBody Map<String,String> params) {
        String wechat_id= String.valueOf(params.get("wechat_id"));
        String id= String.valueOf(params.get("id"));
        String name=String.valueOf(params.get("name"));
        int teacher= Integer.parseInt(params.get("auth"));
        return loginService.register(wechat_id,id,name,teacher);
    }

    @RequestMapping(path = "/logout")
    public ReturnInfo logoff(@RequestBody Map<String,String> params) {
        String wechat_id=params.get("code");
        return loginService.logout(wechat_id);
    }

    @RequestMapping(path = "/login")
    public ReturnInfo login(@RequestBody Map<String,String> params){
        String wechat_id=params.get("code");
        return loginService.login(wechat_id);
    }


}

