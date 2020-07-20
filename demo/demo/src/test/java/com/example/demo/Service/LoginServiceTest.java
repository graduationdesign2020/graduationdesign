package com.example.demo.Service;

import com.example.demo.dao.LoginDao;
import com.example.demo.dao.ProjectDao;
import com.example.demo.dao.StateDao;
import com.example.demo.dao.StudentDao;
import com.example.demo.entity.*;
import com.example.demo.service.LoginService;
import com.example.demo.service.ProcessService;
import com.example.demo.utils.ReturnInfo;
import com.example.demo.utils.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.constant.ReturnMsg.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginServiceTest {
    @Autowired
    private LoginService loginService;
    @Autowired
    private LoginDao loginDao;
    @Autowired
    private ProjectDao projectDao;

    @Test
    public void checkRegister(){
        String wechat_id="123456";
        String id="518021910456";
        String name="lixuan";
        int teacher=0;
        ReturnInfo returnInfo=new ReturnInfo();
        UserInfo userInfo=new UserInfo();
        Users u=loginDao.getUserByWechat_id(wechat_id);
        if(u!=null) {
            returnInfo.setMsg(registerMsg2);
        }
        Users testUser=loginDao.getByIdAndAuth(id,teacher);
        if(testUser!=null) {
            returnInfo.setMsg(registerMsg2);
        }
        boolean flag = false;
            Student student = loginDao.getStudentByIdAndName(id, name);
            if (student != null) {
                userInfo.setId(id);
                userInfo.setOpenid(wechat_id);
                userInfo.setName(student.getName());
                userInfo.setDept(student.getDepartment());
                userInfo.setAuth(0);
                Project project=projectDao.getOne(id);
                userInfo.setProject(project.getProject_name());
                Teacher teacher1=loginDao.getTeacherById(project.getTeacher_id());
                userInfo.setTeacher(teacher1.getName());
                flag = true;
            }
        if (flag) {
            Users users = new Users();
            users.setWechat_id(wechat_id);
            users.setId(id);
            users.setAuth(teacher);
            loginDao.saveUsers(users);
            returnInfo.setMsg(registerMsg1);
            returnInfo.setUserData(userInfo);
        }
        else {
            returnInfo.setMsg(registerMsg0);
        }
        ReturnInfo compare=loginService.register(wechat_id,id,name,teacher);
        assertEquals(compare, returnInfo);
    }

    @Test
    public void checklogout(){
        String wechat_id="123456";
        ReturnInfo returnInfo=new ReturnInfo();
        int flag=loginDao.deleteUsers(wechat_id);
        if(flag==1)
            returnInfo.setMsg(logoutMsg1);
        else returnInfo.setMsg(logoutMsg0);
        ReturnInfo compare=loginService.logout(wechat_id);
        assertEquals(compare, returnInfo);
    }

    @Test
    public void checklogin(){
        String wechat_id="3";
        Users users=loginDao.getUserByWechat_id(wechat_id);
        UserInfo userInfo=new UserInfo();
        ReturnInfo returnInfo=new ReturnInfo();
        if(users!=null){
            String id=users.getId();
            userInfo.setId(id);
            userInfo.setOpenid(wechat_id);
            returnInfo.setMsg(loginMsg1);
            if(users.getAuth()==1){
                Teacher t=loginDao.getTeacherById(id);
                userInfo.setAuth(1);
                userInfo.setDept(t.getDepartment());
                userInfo.setName(t.getName());
                returnInfo.setUserData(userInfo);
            }
            else
            {
                Student student=loginDao.getStudentById(id);
                userInfo.setAuth(0);
                userInfo.setDept(student.getDepartment());
                userInfo.setName(student.getName());
                Project project=projectDao.getOne(id);
                userInfo.setProject(project.getProject_name());
                Teacher teacher=loginDao.getTeacherById(project.getTeacher_id());
                userInfo.setTeacher(teacher.getName());
            }
        }
        else {
            returnInfo.setMsg(loginMsg0);
        }
        ReturnInfo compare=loginService.login(wechat_id);
        assertEquals(compare, returnInfo);
    }
}
