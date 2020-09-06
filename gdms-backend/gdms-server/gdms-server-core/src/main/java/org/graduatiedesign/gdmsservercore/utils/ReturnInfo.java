package org.graduatiedesign.gdmsservercore.utils;

import lombok.Data;

@Data
public class ReturnInfo {
    private String msg;

    private org.graduatiedesign.gdmsservercore.utils.UserInfo userData;
}
