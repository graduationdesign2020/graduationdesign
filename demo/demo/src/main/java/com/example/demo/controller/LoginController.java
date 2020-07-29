package com.example.demo.controller;


import com.example.demo.utils.HttpClient;
import com.example.demo.service.LoginService;
import com.example.demo.utils.CodeReturn;
import com.example.demo.utils.ReturnInfo;
import com.example.demo.constant.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
        String auth = params.get("auth");
        return loginService.register(wechat_id,id,name,auth);
    }

    @RequestMapping(path = "/mylogout")
    public ReturnInfo logoff(@RequestBody Map<String,String> params){
        String wechat_id=params.get("openid");
        return loginService.logout(wechat_id);
    }

    @RequestMapping(path = "/getOpenid")
    public String getopenid(@RequestBody Map<String, String> params) throws IOException{
        String code=params.get("code");
        String result = "";
        try {
            result = HttpClient.doGet(
                    "https://api.weixin.qq.com/sns/jscode2session?appid="
                            +appId.appId+ "&secret="
                            +appId.secret+ "&js_code="
                            + code
                            + "&grant_type=authorization_code", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        CodeReturn openIdJson = mapper.readValue(result, CodeReturn.class);
        String wechat_id=openIdJson.getOpenid();
        return wechat_id;
    }

    @RequestMapping(path = "/mylogin")
    @ResponseBody
    public ReturnInfo login(@RequestBody Map<String,String> params) throws IOException {
        String code=params.get("code");
        System.out.println("login code");
        System.out.print(code);
        String result = "";
        try {
            result = HttpClient.doGet(
                    "https://api.weixin.qq.com/sns/jscode2session?appid="
                            +appId.appId+ "&secret="
                            +appId.secret+ "&js_code="
                            + code
                            + "&grant_type=authorization_code", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        CodeReturn openIdJson = mapper.readValue(result, CodeReturn.class);
        String wechat_id=openIdJson.getOpenid();
        System.out.println(wechat_id);
        ReturnInfo returnInfo = loginService.login(wechat_id);
        System.out.println(returnInfo);
        return returnInfo;
    }

}

