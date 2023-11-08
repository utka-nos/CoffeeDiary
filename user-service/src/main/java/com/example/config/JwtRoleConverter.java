package com.example.config;

import com.example.model.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Конвертер для конвертирования ролей из claims jwt токена в нормальные роли Role с последующей
 *  возможностью реализации авторизации по этим ролям
 */
public class JwtRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        List<String> roles = (List<String>) jwt.getClaims().get("roles");

        if (roles == null || roles.isEmpty()) {
            return new ArrayList<>();
        }

        return roles.stream().map(Role::valueOf).collect(Collectors.toSet());
    }
}
