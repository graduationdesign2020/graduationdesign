package com.example.demo.serviceimpl;

import com.example.demo.dao.LoginDao;
import com.example.demo.dao.ProjectDao;
import com.example.demo.entity.Project;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.Users;
import com.example.demo.service.LoginService;
import com.example.demo.utils.ReturnInfo;
import com.example.demo.utils.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.constant.ReturnMsg.*;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;
    @Autowired
    private ProjectDao projectDao;

    @Override
    public ReturnInfo register(String wechat_id,String id,String name,int teacher){
        ReturnInfo returnInfo=new ReturnInfo();
        UserInfo userInfo=new UserInfo();
        Users u=loginDao.getUserByWechat_id(wechat_id);
        if(u!=null) {
            returnInfo.setMsg(registerMsg2);
            return returnInfo;
        }
        Users testUser=loginDao.getByIdAndAuth(id,teacher);
        if(testUser!=null) {
            returnInfo.setMsg(registerMsg2);
            return returnInfo;
        }
        boolean flag = false;
        if (teacher == 1) {
            Teacher t = loginDao.getTeacherByIdAndName(id, name);
            if (t != null) {
                userInfo.setId(id);
                userInfo.setOpenid(wechat_id);
                userInfo.setName(t.getName());
                userInfo.setDept(t.getDepartment());
                userInfo.setAuth(1);
                flag = true;
            }
        } else {
            System.out.print(id);
            System.out.print(name);
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
        }
        if (flag) {
            Users users = new Users();
            users.setWechat_id(wechat_id);
            users.setId(id);
            users.setAuth(teacher);
            loginDao.saveUsers(users);
            returnInfo.setMsg(registerMsg1);
            returnInfo.setUserData(userInfo);
            return returnInfo;
        }
        else {
            returnInfo.setMsg(registerMsg0);
            return returnInfo;
        }
    }

    @Override
    public ReturnInfo logout(String wechat_id){
        ReturnInfo returnInfo=new ReturnInfo();
        int flag=loginDao.deleteUsers(wechat_id);
        if(flag==1)
            returnInfo.setMsg(logoutMsg1);
        else returnInfo.setMsg(logoutMsg0);
        return returnInfo;
    }

    @Override
    public ReturnInfo login(String wechat_id){
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
                returnInfo.setUserData(userInfo);
            }
        }
        else {
            userInfo.setOpenid(wechat_id);
            returnInfo.setUserData(userInfo);
            returnInfo.setMsg(loginMsg0);
        }
        return returnInfo;
    }


}
