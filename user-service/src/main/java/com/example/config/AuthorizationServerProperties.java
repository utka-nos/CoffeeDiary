package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "spring.security.oauth2.authorization-server")
public class AuthorizationServerProperties {

    private String issuerUrl;
    private String introspectionEndpoint;

}
