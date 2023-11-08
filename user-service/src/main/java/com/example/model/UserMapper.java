package com.example.model;

public class UserMapper {

    public static User toEntity(UserDTO userDTO) {
        User user = new User();

        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setEnabled(userDTO.isEnabled());
        user.setAuthorities(userDTO.getSetOfAuthorities());

        return user;
    }

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setEnabled(user.isEnabled());
        userDTO.setAuthorities(user.getAuthorities());

        return userDTO;
    }

}
