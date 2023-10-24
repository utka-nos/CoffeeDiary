package com.example.model;

import com.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userByUsername = userRepo.findUserByUsername(username);
        return UserMapper.toDTO(userByUsername);
    }

    public UserDTO addNewUser(UserDTO newUserDTO) {
        User newUser = UserMapper.toEntity(newUserDTO);

        User savedUser = userRepo.save(newUser);
        return UserMapper.toDTO(savedUser);
    }

    public UserDTO getUserById(Long id) {
        User userById = userRepo.findUserById(id);
        return UserMapper.toDTO(userById);
    }

    public List<UserDTO> getAllUsers() {
        List<User> allUsers = userRepo.findAll();
        return allUsers.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }
}
