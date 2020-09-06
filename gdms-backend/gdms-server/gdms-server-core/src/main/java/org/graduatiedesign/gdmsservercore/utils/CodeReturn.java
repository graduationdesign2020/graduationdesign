package org.graduatiedesign.gdmsservercore.utils;

import lombok.Data;

@Data
public class CodeReturn {
    private String openid;
    private String session_key;
    private String errcode;
    private String errmsg;

}
