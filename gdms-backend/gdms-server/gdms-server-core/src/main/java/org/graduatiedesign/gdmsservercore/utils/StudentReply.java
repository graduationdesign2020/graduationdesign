package org.graduatiedesign.gdmsservercore.utils;

import lombok.Data;

import java.util.List;

@Data
public class StudentReply {
    private String name;

    private String id;

    private List<Reply> reply;
}
