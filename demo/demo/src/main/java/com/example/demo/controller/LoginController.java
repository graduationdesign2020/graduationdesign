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
        int anInt = Integer.parseInt(params.get("auth"));
        String auth;
        if(anInt==0){
            auth="ROLE_STUDENT";
        }
        else auth="ROLE_TEACHER";
        return loginService.register(wechat_id,id,name,auth);
    }

    @RequestMapping(path = "/mylogout")
    public ReturnInfo logoff(@RequestBody Map<String,String> params){
        String wechat_id=params.get("openid");
        return loginService.logout(wechat_id);
    }

    @RequestMapping(path = "/mylogin")
    @ResponseBody
    public ReturnInfo login(@RequestBody Map<String,String> params) throws IOException {
        String code=params.get("code");
        System.out.println("login code");
        System.out.print(code);
        String result = "";
        try {//请求微信服务器，用code换取openid。HttpUtil是工具类，后面会给出实现，Configure类是小程序配置信息，后面会给出代码
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

//        if (returnInfo.getMsg().equals("SUCCESS")) {
//            String security = "";
//            JSONObject param = new JSONObject();
//            param.put("openid", returnInfo.getUserData().getOpenid());
//            param.put("id", returnInfo.getUserData().getId());
//
//            BufferedReader bufferedReader = null;
//            PrintWriter out = null;
//            try {
//                URL url = new URL("http://localhost:8888/login");
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setConnectTimeout(5000);
//                connection.setRequestMethod("POST");
//                connection.setRequestProperty("Content-Type", "application/json");
//                connection.setDoInput(true);
//                connection.setDoOutput(true);
//                out = new PrintWriter(connection.getOutputStream());
//                out.print(param);
//                out.flush();
//                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
//                String line;
//                while (null != (line = bufferedReader.readLine())) {
//                    security += line;
//                }
//            } catch (Exception e) {
//                System.out.println("发送POST请求出现异常！！！" + e);
//                returnInfo.setMsg("FAIL");
//                e.printStackTrace();
//                return returnInfo;
//            } finally {        //使用finally块来关闭输出流、输入流
//                try {
//                    if (null != out) {
//                        out.close();
//                    }
//                    if (null != bufferedReader) {
//                        bufferedReader.close();
//                    }
//                } catch (Exception e2) {
//                    e2.printStackTrace();
//                }
//            }
//            SecurityInfo securityInfo = mapper.readValue(security, SecurityInfo.class);
//            System.out.println(securityInfo);
//            if (!securityInfo.getCode().equals("200")) {
//                returnInfo.setMsg("FAIL");
//            }
//        }
        return returnInfo;
    }

}

