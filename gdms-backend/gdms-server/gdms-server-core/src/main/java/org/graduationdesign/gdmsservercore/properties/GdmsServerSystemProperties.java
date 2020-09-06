package org.graduationdesign.gdmsservercore.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:gdms-server-core.properties"})
@ConfigurationProperties(prefix = "gdms.server.core")
public class GdmsServerSystemProperties {
    private GdmsSwaggerProperties swagger = new GdmsSwaggerProperties();
    private String anonUrl;
}
