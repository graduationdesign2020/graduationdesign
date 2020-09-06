package org.graduationdesign.gdmsservercore.dao;

import org.graduationdesign.gdmsservercore.entity.Student;

import java.util.List;

public interface StudentDao {
    Student getOne(String stu_id);

    Student getStudentByIdAndName(String id,String name);

    List<Student> findByDept(String dept);
}
