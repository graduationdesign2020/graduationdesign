package org.graduatiedesign.gdmsservercore;

import org.graduationdesign.gdmscommon.annotation.EnableGdmsServerProtect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableDiscoveryClient
@EnableGdmsServerProtect
@EnableScheduling
@SpringBootApplication
public class GdmsServerCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdmsServerCoreApplication.class, args);
    }

}
