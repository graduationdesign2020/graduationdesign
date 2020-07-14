package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reading",schema = "bysj")
public class Reading {
    @EmbeddedId
    private ReadingKey readingKey;

    @Column(name = "is_read")
    private boolean isRead;

}
