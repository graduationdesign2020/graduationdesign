package org.graduationdesign.gdmsservercore.repository;

import org.graduationdesign.gdmsservercore.entity.TeacherMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeacherMessageRepository extends JpaRepository<TeacherMessage,Integer> {
    @Query("from TeacherMessage where teacher_id=:teacher_id order by id desc ")
    List<TeacherMessage> findAllByTeacher_id(String teacher_id);

    @Query("from TeacherMessage where id=:id")
    Optional<TeacherMessage> getById(int id);
}
