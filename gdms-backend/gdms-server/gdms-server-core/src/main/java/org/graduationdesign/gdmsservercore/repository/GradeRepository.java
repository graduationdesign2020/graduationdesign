package org.graduationdesign.gdmsservercore.repository;

import org.graduationdesign.gdmsservercore.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GradeRepository extends JpaRepository<Grade,String> {
    @Query("from Grade where id=:id")
    Grade getById(String id);
}
