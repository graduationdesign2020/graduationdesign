package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Student;
import com.example.demo.utils.HttpClient;
import com.example.demo.service.LoginService;
import com.example.demo.utils.CodeReturn;
import com.example.demo.utils.ReturnInfo;
import com.example.demo.constant.*;
import com.example.demo.utils.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import org.apache.http.client.methods.HttpPost;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_TEACHER')")
    public ReturnInfo logoff(Authentication authentication){
        return loginService.logout(authentication.getName());
    }

    @RequestMapping(path = "/getAuth")
    @PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_TEACHER')")
    public Integer getauth(Authentication authentication){
        if(authentication.getAuthorities().toArray()[0].toString().equals("ROLE_STUDENT"))
        return 0;
        if(authentication.getAuthorities().toArray()[0].toString().equals("ROLE_TEACHER"))
            return 1;
        return null;
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
        return openIdJson.getOpenid();
    }

    @RequestMapping(path = "/getUserData")
    public UserInfo getuserdata(Authentication authentication){
        return loginService.getUserData(authentication.getName(), authentication.getAuthorities().toArray()[0].toString());
    }

    @RequestMapping(path = "/adminlogin")
    public JSONObject adminLogin(@RequestBody Map<String, String> params) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("id", params.get("id"));
        data.put("openid", params.get("openid"));
        try {
            res = HttpClient.doPost("http://10.162.186.199:8888/login", data);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}

