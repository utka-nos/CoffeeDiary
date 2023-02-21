package com.example.mapper;

import com.example.dto.UserDTO;
import com.example.entity.User;

public class UserMapper {

    public static User toEntity(UserDTO userDTO) {
        User user = new User();

        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());

        return user;
    }

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());

        return userDTO;
    }

}
