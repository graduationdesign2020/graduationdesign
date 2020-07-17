package com.example.demo.repository;

import com.example.demo.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, String> {
    List<Project> findAllByTeacher_id(String tea_id);
    @Query("select id from Project where teacher_id=:t_id")
    List<String> getIdByTeacher_id(String t_id);
}
