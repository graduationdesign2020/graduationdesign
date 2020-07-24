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
        if (auth.equals("ROLE_TEACHER")) {
            Teacher t = teacherDao.getTeacherByIdAndName(id, name);
            if (t != null) {
                userInfo.setId(id);
                userInfo.setOpenid(wechat_id);
                userInfo.setName(t.getName());
                userInfo.setDept(t.getDepartment());
                userInfo.setAuth("ROLE_TEACHER");
                flag = true;
            }
        } else {
            System.out.print(id);
            System.out.print(name);
            Student student = studentDao.getStudentByIdAndName(id, name);
            if (student != null) {
                userInfo.setId(id);
                userInfo.setOpenid(wechat_id);
                userInfo.setName(student.getName());
                userInfo.setDept(student.getDepartment());
                userInfo.setAuth("ROLE_STUDENT");
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
            users.setAuth("ROLE_TEACHER");
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
    public ReturnInfo logout(String wechat_id){
        ReturnInfo returnInfo=new ReturnInfo();
        int flag= usersDao.deleteUsers(wechat_id);
        if(flag==1)
            returnInfo.setMsg(Msg1);
        else returnInfo.setMsg(Msg0);
        return returnInfo;
    }

    @Override
    public ReturnInfo login(String wechat_id){
        Optional<Users> users= usersDao.getUserByWechat_id(wechat_id);
        UserInfo userInfo=new UserInfo();
        ReturnInfo returnInfo=new ReturnInfo();
        if(users.isPresent()){
            Users u=users.get();
            String id=u.getId();
            userInfo.setId(id);
            userInfo.setOpenid(wechat_id);
            returnInfo.setMsg(Msg1);
            if(u.getAuth().equals("ROLE_TEACHER")){
                Teacher t= teacherDao.getTeacherById(id);
                userInfo.setAuth("ROLE_TEACHER");
                userInfo.setDept(t.getDepartment());
                userInfo.setName(t.getName());
                returnInfo.setUserData(userInfo);
            }
            else
            {
                Student student= studentDao.getOne(id);
                userInfo.setAuth("ROLE_STUDENT");
                userInfo.setDept(student.getDepartment());
                userInfo.setName(student.getName());
                Optional<Project> project=projectDao.getOne(id);
                if(project.isPresent())
                {
                    Project p=project.get();
                    userInfo.setProject(p.getProject_name());
                    Teacher teacher = teacherDao.getTeacherById(p.getTeacher_id());
                    userInfo.setTeacher(teacher.getName());
                    returnInfo.setUserData(userInfo);
                }
            }
        }
        else {
            userInfo.setOpenid(wechat_id);
            returnInfo.setUserData(userInfo);
            returnInfo.setMsg(Msg0);
        }
        return returnInfo;
    }


}
