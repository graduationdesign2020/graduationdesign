package org.gdms.test.controller;

import org.gdms.test.service.LoginService;
import org.gdms.test.util.*;
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
    public ReturnInfo register(@RequestBody Map<String,String> params){
        System.out.println(params);
        String wechat_id= params.get("openid");
        String id= params.get("id");
        String name=params.get("name");
        int anInt = Integer.parseInt(params.get("auth"));
        String auth;
        if(anInt==0){
            auth="ROLE_STUDENT";
        }
        else auth="ROLE_TEACHER";
        return loginService.register(wechat_id,id,name,auth);
    }

    @RequestMapping(path = "/mylogout")
    public ReturnInfo logoff(@RequestBody Map<String, String> params){
        String id=params.get("id");
        return loginService.logout(id);
    }

    @RequestMapping(path = "/getUserData")
    public UserInfo getuserdata(@RequestBody Map<String, String> params){
        String id=params.get("id");
        String auth=params.get("auth");
        return loginService.getUserData(id, auth);
    }
}

