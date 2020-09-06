package org.graduationdesign.gdmsservernotice.configure;

import org.apache.commons.lang.StringUtils;
import org.graduationdesign.gdmsservernotice.properties.GdmsServerSystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

public class GdmsServerNoticeResourceSeverConfigure extends ResourceServerConfigurerAdapter {
    @Autowired
    private GdmsServerSystemProperties properties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");

        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers(anonUrls).permitAll()
                .antMatchers("/**").authenticated();
    }
}
