package com.example.demo.daoimpl;

import com.example.demo.dao.ProjectDao;
import com.example.demo.entity.Project;
import com.example.demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDaoImpl implements ProjectDao {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project getOne(String id) {
        return projectRepository.getOne(id);
    }

    @Override
    public List<Project> findByTeacher(String tea_id) {
        return projectRepository.findAllByTeacher_id(tea_id);
    }
}
