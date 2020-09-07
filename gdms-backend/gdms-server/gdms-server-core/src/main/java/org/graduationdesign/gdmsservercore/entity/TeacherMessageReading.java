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
@Table(name = "teachermessagereading",schema = "GDMS")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class TeacherMessageReading {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @NotFound(action= NotFoundAction.IGNORE)
    private int id;

    @Basic
    private int message_id;

    @Basic
    private String student_id;

    @Basic
    private boolean is_read;

    @Transient
    private ReplyMessage reply;
}
