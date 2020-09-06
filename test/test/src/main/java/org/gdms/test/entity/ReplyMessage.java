package org.gdms.test.entity;

import org.gdms.test.util.Reply;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

@Data
@Document(collection = "replymessage")
public class ReplyMessage {
    @Id
    @Column(name = "_id")
    private int id;

    private List<Reply> reply;

}
