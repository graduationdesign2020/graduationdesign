package org.graduationdesign.gdmsservercore.serviceimpl;

import org.graduationdesign.gdmsservercore.dao.StudentDao;
import org.graduationdesign.gdmsservercore.dao.TeacherDao;
import org.graduationdesign.gdmsservercore.dao.UserDao;
import org.graduationdesign.gdmsservercore.dao.ProjectDao;
import org.graduationdesign.gdmsservercore.entity.Project;
import org.graduationdesign.gdmsservercore.entity.Student;
import org.graduationdesign.gdmsservercore.entity.Teacher;
import org.graduationdesign.gdmsservercore.entity.User;
import org.graduationdesign.gdmsservercore.service.LoginService;
import org.graduationdesign.gdmsservercore.utils.ReturnInfo;
import org.graduationdesign.gdmsservercore.utils.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import static org.graduationdesign.gdmsservercore.constant.ReturnMsg.*;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDao usersDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ProjectDao projectDao;

    @Override
    public ReturnInfo register(String wechat_id, String id, String name, String auth){
        ReturnInfo returnInfo=new ReturnInfo();
        UserInfo userInfo=new UserInfo();
        Optional<User> u= usersDao.getUserByWechat(wechat_id);
        if(u.isPresent()) {
            returnInfo.setMsg(registerMsg2);
            return returnInfo;
        }
        Optional<User> testUser= usersDao.getByIdAndAuth(id,auth);
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
                System.out.print("student not null");
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
            User users = new User();
            users.setWechat(wechat_id);
            users.setId(id);
            users.setAuth(auth);
            users.setEnabled(true);
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
