package org.graduationdesign.gdms_notice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;


@Data
@Document(collection = "schoolnoticecontent")
public class SchoolNoticeContent {
    @Id
    @Column(name = "_id")
    private int id;

    private int type;//type=0

    private String title;

    private String content;
}
