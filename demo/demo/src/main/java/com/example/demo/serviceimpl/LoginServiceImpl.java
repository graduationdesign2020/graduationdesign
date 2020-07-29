package com.example.demo.serviceimpl;

import com.example.demo.dao.StudentDao;
import com.example.demo.dao.TeacherDao;
import com.example.demo.dao.UsersDao;
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

import java.util.Optional;

import static com.example.demo.constant.ReturnMsg.*;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ProjectDao projectDao;

    @Override
    public ReturnInfo register(String wechat_id,String id,String name,String auth){
        ReturnInfo returnInfo=new ReturnInfo();
        UserInfo userInfo=new UserInfo();
        Optional<Users> u= usersDao.getUserByWechat_id(wechat_id);
        if(u.isPresent()) {
            returnInfo.setMsg("registerMsg2");
            return returnInfo;
        }
        Optional<Users> testUser= usersDao.getByIdAndAuth(id,auth);
        if(testUser.isPresent()) {
            returnInfo.setMsg(registerMsg2);
            return returnInfo;
        }
        boolean flag = false;
        System.out.print(auth);
        if (auth.equals("ROLE_TEACHER")) {
            System.out.print("teacher");
            Teacher t = teacherDao.getTeacherByIdAndName(id, name);
            if (t != null) {
                userInfo.setId(id);
                userInfo.setName(t.getName());
                userInfo.setDept(t.getDepartment());
                flag = true;
            }
        } else {
            System.out.print(id);
            System.out.print(name);
            Student student = studentDao.getStudentByIdAndName(id, name);
            if (student != null) {
                userInfo.setId(id);
                userInfo.setName(student.getName());
                userInfo.setDept(student.getDepartment());
                Optional<Project> project= projectDao.getOne(id);
                if(project.isPresent()) {
                    Project p=project.get();
                    userInfo.setProject(p.getProject_name());
                    Teacher teacher1= teacherDao.getTeacherById(p.getTeacher_id());
                    userInfo.setTeacher(teacher1.getName());
                }
                flag = true;
            }
        }
        if (flag) {
            Users users = new Users();
            users.setWechat_id(wechat_id);
            users.setId(id);
            users.setAuth(auth);
            usersDao.saveUsers(users);
            returnInfo.setMsg(Msg1);
            returnInfo.setUserData(userInfo);
            return returnInfo;
        }
        else {
            returnInfo.setMsg(registerMsg0);
            return returnInfo;
        }
    }

    @Override
    public ReturnInfo logout(String id){
        ReturnInfo returnInfo=new ReturnInfo();
        int flag= usersDao.deleteUsers(id);
        if(flag==1)
            returnInfo.setMsg(Msg1);
        else returnInfo.setMsg(Msg0);
        return returnInfo;
    }

    @Override
    public UserInfo getUserData(String id, String role){
        UserInfo userInfo=new UserInfo();
        userInfo.setId(id);
        if(role.equals("ROLE_TEACHER")){
            Teacher t= teacherDao.getTeacherById(id);
            if(t!=null)
            {
                userInfo.setDept(t.getDepartment());
                userInfo.setName(t.getName());
            }
        }
        if(role.equals("ROLE_STUDENT")){
            Student student= studentDao.getOne(id);
            if(student!=null)
            {
                userInfo.setDept(student.getDepartment());
                userInfo.setName(student.getName());
                Optional<Project> project = projectDao.getOne(id);
                if (project.isPresent()) {
                    Project p = project.get();
                    userInfo.setProject(p.getProject_name());
                    Teacher teacher = teacherDao.getTeacherById(p.getTeacher_id());
                    userInfo.setTeacher(teacher.getName());
                }
            }
        }
        return userInfo;
    }

}
