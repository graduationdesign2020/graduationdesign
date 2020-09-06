package org.graduationdesign.gdmsservercore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "grade",schema = "GDMS")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Grade {
    @Id
    private String id;

    @Column(name = "teacher_grade")
    private String teachergrade;

    @Column(name = "review_grade")
    private String reviewgrade;

    @Column(name = "defense_grade")
    private String thesisgrade;

    @Column(name = "total_grade")
    private String allgrade;
}
