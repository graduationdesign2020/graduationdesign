package org.graduationdesign.gdmsservercore.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecurityInfo {
    private String code;
    private String msg;
    //private Authentication data;
}
