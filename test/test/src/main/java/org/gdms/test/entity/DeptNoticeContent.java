package org.gdms.test.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;

@Data
@Document(collection = "deptnoticecontent")
public class DeptNoticeContent {
    @Id
    @Column(name = "_id")
    private int id;

    private int type;//type=1

    private String title;

    private String department;

    private String content;
}
