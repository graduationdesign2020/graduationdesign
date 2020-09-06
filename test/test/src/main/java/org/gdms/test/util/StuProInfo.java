package org.gdms.test.util;

import lombok.Data;

import java.util.List;

@Data
public class StuProInfo {
    private int state;
    private List<StuInfo> stuInfos;
}
