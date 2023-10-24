package com.example.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO implements UserDetails{

    @JsonView(UserJsonView.MainInfo.class)
    private Long id;
    @JsonView(UserJsonView.MainInfo.class)
    private String username;
    @JsonView(UserJsonView.FullInfo.class)
    private Set<Role> roles = new HashSet<>();
    @JsonView(UserJsonView.FullInfo.class)
    private String password;

    @Override
    @JsonView(UserJsonView.FullInfo.class)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    @JsonView(UserJsonView.FullInfo.class)
    public String getPassword() {
        return password;
    }

    @Override
    @JsonView(UserJsonView.MainInfo.class)
    public String getUsername() {
        return username;
    }

    @Override
    @JsonView(UserJsonView.FullInfo.class)
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonView(UserJsonView.FullInfo.class)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonView(UserJsonView.FullInfo.class)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonView(UserJsonView.FullInfo.class)
    public boolean isEnabled() {
        return true;
    }
}
