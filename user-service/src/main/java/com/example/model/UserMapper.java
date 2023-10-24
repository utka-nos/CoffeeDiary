package com.example.model;

public class UserMapper {

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setRoles(userDTO.getRoles());
        user.setPassword(userDTO.getPassword());

        return user;
    }

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setRoles(user.getRoles());
        userDTO.setPassword(user.getPassword());

        return userDTO;
    }

}
