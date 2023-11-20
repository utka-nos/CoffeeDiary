package com.example.service;

import com.example.UserDTO;
import com.example.model.User;
import com.example.model.UserMapper;
import com.example.repo.UserRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserMapper.toDTO(userRepo.findByUsername(username));
    }

    public UserDTO addNewUser(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User savedUser = userRepo.save(UserMapper.toEntity(userDTO));
        return UserMapper.toDTO(savedUser);
    }

    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }

    public List<UserDTO> getAllUsers() {
        return userRepo.findAll().stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }
}
