package org.graduationdesign.gdmsservercore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "teachermessage",schema = "GDMS")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class TeacherMessage {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @NotFound(action= NotFoundAction.IGNORE)
    private int id;

    @Basic
    private String title;

    @Basic
    private String teacher_id;

    @Basic
    private String time;

    @Basic
    private int type;

    @Transient
    private TeacherMessageContent teacherMessageContent;

}
