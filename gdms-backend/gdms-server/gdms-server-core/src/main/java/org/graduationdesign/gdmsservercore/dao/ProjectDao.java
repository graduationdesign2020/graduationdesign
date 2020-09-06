package org.graduationdesign.gdmsservercore.dao;

import org.graduationdesign.gdmsservercore.entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectDao {
    Optional<Project> getOne(String id);
    List<Project> findByTeacher(String tea_id);
    List<String> getIdByTeacher_id(String t_id);
}
