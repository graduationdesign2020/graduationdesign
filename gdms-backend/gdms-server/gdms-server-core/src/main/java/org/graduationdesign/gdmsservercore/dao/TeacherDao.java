package org.graduationdesign.gdmsservercore.dao;

import org.graduationdesign.gdmsservercore.entity.Teacher;

public interface TeacherDao {
    Teacher getTeacherById(String id);

    Teacher getTeacherByIdAndName(String id,String name);
}
