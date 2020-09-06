package org.graduatiedesign.gdmsservercore.utils;

import lombok.Data;

import java.util.List;

@Data
public class ReplyMessageInfo {
    private int id;

    private int type;//0:单向消息;1:回复消息

    private int reading_id;

    private String title;

    private String teachername;

    private String content;

    private boolean isread;

    private String time;

    private List<Reply> reply;
}
