package org.graduationdesign.gdmsauth;

import org.graduationdesign.gdmscommon.annotation.EnableGdmsServerProtect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableGdmsServerProtect
public class GdmsAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdmsAuthApplication.class, args);
    }

}
