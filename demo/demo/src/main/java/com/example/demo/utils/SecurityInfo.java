package com.example.demo.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.security.core.Authentication;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecurityInfo {
    private String code;
    private String msg;
    //private Authentication data;
}
