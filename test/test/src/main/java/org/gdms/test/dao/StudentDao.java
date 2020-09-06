package org.gdms.test.dao;

import org.gdms.test.entity.Student;

import java.util.List;

public interface StudentDao {
    String getDeptById(String id);

    Student getOne(String stu_id);

    Student getStudentByIdAndName(String id,String name);

    List<Student> findByDept(String dept);
}
