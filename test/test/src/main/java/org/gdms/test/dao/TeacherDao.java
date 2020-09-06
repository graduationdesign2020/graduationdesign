package org.gdms.test.dao;

import org.gdms.test.entity.Teacher;

public interface TeacherDao {
    String getDeptById(String id);

    Teacher getTeacherById(String id);

    Teacher getTeacherByIdAndName(String id, String name);
}
