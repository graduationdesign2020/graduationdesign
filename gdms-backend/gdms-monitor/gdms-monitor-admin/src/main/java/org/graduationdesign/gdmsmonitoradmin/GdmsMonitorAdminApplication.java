package org.graduationdesign.gdmsmonitoradmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class GdmsMonitorAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdmsMonitorAdminApplication.class, args);
    }

}
