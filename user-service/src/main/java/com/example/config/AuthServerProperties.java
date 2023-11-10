package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "spring.security.oauth2.authorization-server")
@Data
@Configuration
public class AuthServerProperties {

    private String issuerUrl;

}
