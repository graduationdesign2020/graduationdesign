package org.graduationdesign.gdmsserversearch;

import org.graduationdesign.gdmscommon.annotation.EnableGdmsServerProtect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableDiscoveryClient
@EnableGdmsServerProtect
@SpringBootApplication
public class GdmsServerSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdmsServerSearchApplication.class, args);
    }

}
