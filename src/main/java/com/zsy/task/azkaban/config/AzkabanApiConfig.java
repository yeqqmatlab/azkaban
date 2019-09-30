package com.zsy.task.azkaban.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yqq on 2019/9/30.
 */
@Configuration
public class AzkabanApiConfig {

    @Value("${azkaban.url}")
    private String uri;

    @Value("${azkaban.username}")
    private String username;

    @Value("${azkaban.password}")
    private String password;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
