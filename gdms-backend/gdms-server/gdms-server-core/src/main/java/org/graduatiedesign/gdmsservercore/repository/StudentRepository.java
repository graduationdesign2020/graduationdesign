package org.graduatiedesign.gdmsservercore.repository;

import org.graduatiedesign.gdmsservercore.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("from Student where id=:id and name=:name")
    Student getByIdAndName(String id,String name);

    @Query("from Student where id=:id")
    Student getById(String id);

    List<Student> findAllByDepartment(String dept);

    @Query("select department from Student where id=:id")
    String getDeptById(String id);
}
