package org.gdms.test.serviceimpl;


import org.gdms.test.TestApplicationTests;
import org.gdms.test.service.LoginService;
import org.gdms.test.util.ReturnInfo;
import org.gdms.test.util.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LoginServiceTest extends TestApplicationTests {
    @Autowired
    private LoginService loginService;

    @Test
    @Transactional
    public void checkrigisterstudent(){
        ReturnInfo compare=loginService.register("111111","111","1","ROLE_STUDENT");
        ReturnInfo returnInfo=new ReturnInfo();
        returnInfo.setMsg("SUCCESS");
        UserInfo userInfo=new UserInfo();
        userInfo.setId("111");
        userInfo.setName("1");
        userInfo.setTeacher("aaa");
        userInfo.setDept("1");
        userInfo.setProject("1");
        returnInfo.setUserData(userInfo);
        assertEquals(returnInfo,compare);
    }
    @Test
    @Transactional
    public void checkrigisterteacher(){
        ReturnInfo compare=loginService.register("100200300","abcdefg","aaa","ROLE_TEACHER");
        ReturnInfo returnInfo=new ReturnInfo();
        returnInfo.setMsg("SUCCESS");
        UserInfo userInfo=new UserInfo();
        userInfo.setId("abcdefg");
        userInfo.setName("aaa");
        userInfo.setTeacher(null);
        userInfo.setDept("aaa");
        userInfo.setProject(null);
        returnInfo.setUserData(userInfo);
        assertEquals(returnInfo,compare);
    }
    @Test
    @Transactional
    public void checkrigisterwrong(){
        ReturnInfo compare=loginService.register("100200300","1234","aaa","ROLE_TEACHER");
        ReturnInfo returnInfo=new ReturnInfo();
        returnInfo.setMsg("WRONG");
        assertEquals(returnInfo,compare);
    }
    @Test
    @Transactional
    public void checkrigisteridrepeat(){
        ReturnInfo compare=loginService.register("100200300","03047a","aaa","ROLE_TEACHER");
        ReturnInfo returnInfo=new ReturnInfo();
        returnInfo.setMsg("REGISTERED");
        assertEquals(returnInfo,compare);
    }
    @Test
    @Transactional
    public void checkrigisterwechatrepeat(){
        ReturnInfo compare=loginService.register("03047a","03047a","aaa","ROLE_TEACHER");
        ReturnInfo returnInfo=new ReturnInfo();
        returnInfo.setMsg("REGISTERED");
        assertEquals(returnInfo,compare);
    }
    @Test
    @Transactional
    public void checklogout(){
        String wechat_id="123456";
        ReturnInfo returnInfo=new ReturnInfo();
        returnInfo.setMsg("FAIL");
        ReturnInfo compare=loginService.logout(wechat_id);
        assertEquals(compare, returnInfo);
    }


    @Test
    @Transactional
    public void checklogoutsuccess(){
        String wechat_id="816249335787";
        ReturnInfo returnInfo=new ReturnInfo();
        returnInfo.setMsg("SUCCESS");
        ReturnInfo compare=loginService.logout(wechat_id);
        assertEquals(compare, returnInfo);
    }

    @Test
    @Transactional
    public void checklogin(){
        String wechat_id="305349154743";
        UserInfo compare=loginService.getUserData(wechat_id,"ROLE_STUDENT");
        UserInfo userInfo=new UserInfo();
        userInfo.setName("学生");
        userInfo.setId("305349154743");
        userInfo.setDept("电子信息与电气工程学院");
        userInfo.setProject("项目名称");
        userInfo.setTeacher("孙焱");
        assertEquals(compare, userInfo);
    }

    @Test
    @Transactional
    public void checkloginteacher(){
        String wechat_id="03047a";
        UserInfo compare=loginService.getUserData(wechat_id,"ROLE_TEACHER");
        UserInfo userInfo=new UserInfo();
        userInfo.setName("饶若楠");
        userInfo.setId("03047a");
        userInfo.setDept("电子信息与电气工程学院");
        userInfo.setProject(null);
        userInfo.setTeacher(null);
        assertEquals(compare, userInfo);
    }
}
