package org.graduationdesign.gdmsservertest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GdmsServerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdmsServerTestApplication.class, args);
    }

}
