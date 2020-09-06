package org.graduatiedesign.gdmsservercore.entity;

import org.graduatiedesign.gdmsservercore.utils.Reply;
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
