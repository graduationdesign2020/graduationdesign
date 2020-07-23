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
<<<<<<< HEAD
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private ProjectDao projectDao;

    @Test
    public void checkRegister() {
        String wechat_id = "123456";
        String id = "518021910456";
        String name = "lixuan";
        int teacher = 0;
        ReturnInfo returnInfo = new ReturnInfo();
        UserInfo userInfo = new UserInfo();
        Optional<Users> u = usersDao.getUserByWechat_id(wechat_id);
        if (u.isPresent()) {
            returnInfo.setMsg(registerMsg2);
        }
        Optional<Users> testUser = usersDao.getByIdAndAuth(id, teacher);
        if (testUser.isPresent()) {
            returnInfo.setMsg(registerMsg2);
        }
        boolean flag = false;
        Student student = studentDao.getStudentByIdAndName(id, name);
        if (student != null) {
            userInfo.setId(id);
            userInfo.setOpenid(wechat_id);
            userInfo.setName(student.getName());
            userInfo.setDept(student.getDepartment());
            userInfo.setAuth("ROLE_STUDENT");
            Optional<Project> project = projectDao.getOne(id);
            userInfo.setProject(project.get().getProject_name());
            Teacher teacher1 = teacherDao.getTeacherById(project.get().getTeacher_id());
            userInfo.setTeacher(teacher1.getName());
            flag = true;
        }
        if (flag) {
            Users users = new Users();
            users.setWechat_id(wechat_id);
            users.setId(id);
            users.setAuth("ROLE_TEACHER");
            usersDao.saveUsers(users);
            returnInfo.setMsg(registerMsg1);
            returnInfo.setUserData(userInfo);
        } else {
            returnInfo.setMsg(registerMsg0);
        }
        ReturnInfo compare = loginService.register(wechat_id, id, name, teacher);
        assertEquals(compare, returnInfo);
    }
=======
>>>>>>> 18079e399bc096e608f0d15a68030ef48aed7570

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
<<<<<<< HEAD
        if(users.isPresent()){
            String id=users.get().getId();
            userInfo.setId(id);
            userInfo.setOpenid(wechat_id);
            returnInfo.setMsg(loginMsg1);
            if(users.get().getAuth().equals("ROLE_TEACHER")){
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
                userInfo.setProject(project.get().getProject_name());
                Teacher teacher= teacherDao.getTeacherById(project.get().getTeacher_id());
                userInfo.setTeacher(teacher.getName());
            }
        }
        else {
            returnInfo.setMsg(loginMsg0);
        }
=======

>>>>>>> 18079e399bc096e608f0d15a68030ef48aed7570
        ReturnInfo compare=loginService.login(wechat_id);
        UserInfo userInfo=new UserInfo();
        userInfo.init("4","ohdPd4pTA-yKZTSfrSY6DsB5_Y00","tiger","SE",null,null,1);
        returnInfo.setUserData(userInfo);
        assertEquals(compare, returnInfo);
    }
}
