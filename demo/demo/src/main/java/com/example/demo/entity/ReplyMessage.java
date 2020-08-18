package com.example.demo.entity;

import com.example.demo.utils.Reply;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

import java.util.List;

@Data
@Document(collection = "replymessage")
public class ReplyMessage {
    @Id
    @Column(name = "_id")
    private int id;

    private List<Reply> reply;

}
