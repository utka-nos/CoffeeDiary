package com.example.config;

import org.example.config.JwtContext;
import org.example.config.JwtRoleConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

@Configuration(proxyBeanMethods = false)
@Import({JwtContext.class})
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new JwtRoleConverter());
        HeaderWriterLogoutHandler clearSiteData =
                new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.ALL));

        return httpSecurity
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers("/users/add").permitAll()
                                .requestMatchers("/error").permitAll()
                                .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter)))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .logout(logout -> logout.addLogoutHandler(clearSiteData).logoutSuccessUrl("http://localhost:3000/home"))
                .formLogin(Customizer.withDefaults()).build();
    }

}
