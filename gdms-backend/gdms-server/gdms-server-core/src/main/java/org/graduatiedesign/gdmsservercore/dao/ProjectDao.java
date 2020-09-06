package org.graduatiedesign.gdmsservercore.dao;

import org.graduatiedesign.gdmsservercore.entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectDao {
    Optional<Project> getOne(String id);
    List<Project> findByTeacher(String tea_id);
    List<String> getIdByTeacher_id(String t_id);
}
