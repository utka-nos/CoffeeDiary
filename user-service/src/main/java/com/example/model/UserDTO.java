package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.config.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements UserDetails {

    @JsonView(UserJsonView.MainInfo.class)
    private Long id;

    @JsonView(UserJsonView.MainInfo.class)
    private String username;

    @JsonView(UserJsonView.PasswordInfo.class)
    private String password;

    @JsonView(UserJsonView.FullInfo.class)
    private String email;

    @JsonView(UserJsonView.FullInfo.class)
    private boolean enabled;

    @JsonView(UserJsonView.FullInfo.class)
    private Set<Role> authorities = new HashSet<>();

    @JsonIgnore
    public Set<Role> getSetOfAuthorities() {
        return authorities;
    }

    @JsonView(UserJsonView.FullInfo.class)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream().toList();
    }

    @JsonView(UserJsonView.FullInfo.class)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonView(UserJsonView.FullInfo.class)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonView(UserJsonView.FullInfo.class)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonView(UserJsonView.FullInfo.class)
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public UserDTO(String username, Long id) {
        this.username = username;
        this.id = id;
    }
}
