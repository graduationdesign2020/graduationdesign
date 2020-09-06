package org.graduatiedesign.gdmsservercore.dao;

import org.graduatiedesign.gdmsservercore.entity.Teacher;

public interface TeacherDao {
    Teacher getTeacherById(String id);

    Teacher getTeacherByIdAndName(String id,String name);
}
