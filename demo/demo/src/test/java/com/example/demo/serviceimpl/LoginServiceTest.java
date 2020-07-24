package com.example.demo.serviceimpl;


import com.example.demo.service.LoginService;
import com.example.demo.utils.ReturnInfo;
import com.example.demo.utils.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LoginServiceTest {
    @Autowired
    private LoginService loginService;

    @Test
    @Transactional
    public void checklogout(){
        String wechat_id="123456";
        ReturnInfo returnInfo=new ReturnInfo();
        returnInfo.setMsg("SUCCESS");
        ReturnInfo compare=loginService.logout(wechat_id);
        assertEquals(compare, returnInfo);
    }

    @Test
    @Transactional
    public void checklogin(){
        String wechat_id="ohdPd4pTA-yKZTSfrSY6DsB5_Y00";
        ReturnInfo returnInfo=new ReturnInfo();
        ReturnInfo compare=loginService.login(wechat_id);
        UserInfo userInfo=new UserInfo();
        userInfo.init("4","ohdPd4pTA-yKZTSfrSY6DsB5_Y00","tiger","SE",null,null,"teacher");
        returnInfo.setUserData(userInfo);
        assertEquals(compare, returnInfo);
    }
}
