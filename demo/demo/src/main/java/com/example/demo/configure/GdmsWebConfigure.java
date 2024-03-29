package org.graduationdesign.gdmsservernotice.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class GdmsWebConfigure {
    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.graduationdesign.gdmsservernotice.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "GDMS-Server-Search API",
                "GDMS-Server-Search API.",
                "1.0",
                null,
                new Contact("GDMS", "https://github.com/graduationdesign2020/graduationdesign", "riken01@sjtu.edu.cn"),
                "Apache 2.0", "https://www.apache.org/licenses/LICENSE-2.0.html", Collections.emptyList());
    }
}
