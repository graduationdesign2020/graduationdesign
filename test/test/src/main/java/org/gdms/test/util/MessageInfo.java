package org.gdms.test.util;

import lombok.Data;

@Data
public class MessageInfo {
    private int id;

    private int type;//0:单向消息;1:回复消息

    private int reading_id;

    private String title;

    private String teachername;

    private String content;

    private boolean isread;

    private int reading;

    private int unread;

    private String time;

    public void init(int id,int reading_id,String title,String teachername,String content,String time,boolean is_read,int reading,int unread)
    {
        this.id=id;
        this.reading_id=reading_id;
        this.teachername=teachername;
        this.time=time;
        this.title=title;
        this.content=content;
        this.reading=reading;
        this.unread=unread;
        this.isread=is_read;
    }
}