package org.graduationdesign.gdmsregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class GdmsRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdmsRegisterApplication.class, args);
    }

}
