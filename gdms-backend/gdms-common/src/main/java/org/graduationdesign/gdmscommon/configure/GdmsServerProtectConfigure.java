package org.graduationdesign.gdmscommon.configure;

import org.graduationdesign.gdmscommon.interceptor.GdmsServerProtectInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class GdmsServerProtectConfigure implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GdmsServerProtectInterceptor());
    }
}
