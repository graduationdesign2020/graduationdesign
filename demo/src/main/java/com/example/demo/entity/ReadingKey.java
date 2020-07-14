package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ReadingKey implements Serializable {
    @Column(name = "message_id")
    private int messageId;

    @Column(name = "student_id")
    private String studentId;
}
