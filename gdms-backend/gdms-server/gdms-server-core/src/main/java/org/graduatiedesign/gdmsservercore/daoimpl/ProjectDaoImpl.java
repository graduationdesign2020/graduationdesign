package org.graduatiedesign.gdmsservercore.daoimpl;

import org.graduatiedesign.gdmsservercore.dao.ProjectDao;
import org.graduatiedesign.gdmsservercore.entity.Project;
import org.graduatiedesign.gdmsservercore.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProjectDaoImpl implements ProjectDao {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Optional<Project> getOne(String id) {
        return projectRepository.getById(id);
    }

    @Override
    public List<Project> findByTeacher(String tea_id) {
        return projectRepository.findAllByTeacher(tea_id);
    }

    @Override
    public List<String> getIdByTeacher_id(String t_id){
        return projectRepository.getIdByTeacher_id(t_id);
    }

}
