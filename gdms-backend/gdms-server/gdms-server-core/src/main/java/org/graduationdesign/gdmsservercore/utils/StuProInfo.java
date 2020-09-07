package org.graduationdesign.gdmsservercore.utils;

import lombok.Data;

import java.util.List;

@Data
public class StuProInfo {
    private int state;
    private List<StuInfo> stuInfos;
}
