package com.example.demo.serviceimpl;

import com.example.demo.dao.LoginDao;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.Users;
import com.example.demo.service.LoginService;
import com.example.demo.utils.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.constant.ReturnMsg.*;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;
    @Override
    public String register(String wechat_id,String id,String name,int teacher){
        Users u=loginDao.getUserByWechat_id(wechat_id);
        if(u!=null) {
            return registerMsg2;
        }
        Users testUser=loginDao.getByIdAndAuth(id,teacher);
        if(testUser!=null) {
            return registerMsg2;
        }
        boolean flag = false;
        if (teacher == 1) {
            Teacher t = loginDao.getTeacherByIdAndName(id, name);
            if (t != null) flag = true;
        } else {
            Student student = loginDao.getStudentByIdAndName(id, name);
            if (student != null) flag = true;
        }
        if (flag) {
            Users users = new Users();
            users.setWechat_id(wechat_id);
            users.setId(id);
            users.setAuth(teacher);
            loginDao.saveUsers(users);
            return registerMsg1;
        }
        else return registerMsg0;
    }

    @Override
    public String logout(String wechat_id){
        int flag=loginDao.deleteUsers(wechat_id);
        if(flag==1)
            return logoutMsg1;
        else return logoutMsg0;
    }

    @Override
    public UserInfo login(String wechat_id){
        Users users=loginDao.getUserByWechat_id(wechat_id);
        UserInfo userInfo=new UserInfo();
        if(users!=null){
            String id=users.getId();
            userInfo.setId(id);
            userInfo.setMsg(loginMsg1);
            if(users.getAuth()==1){
                Teacher t=loginDao.getTeacherById(id);
                userInfo.setAuth(1);
                userInfo.setMajor(t.getMajor());
                userInfo.setDept(t.getDepartment());
                userInfo.setName(t.getName());
            }
            else
            {
                Student student=loginDao.getStudentById(id);
                userInfo.setAuth(0);
                userInfo.setMajor(student.getMajor());
                userInfo.setDept(student.getDepartment());
                userInfo.setName(student.getName());
            }
        }
        else {
            userInfo.setMsg(loginMsg0);
        }
        return userInfo;
    }


}