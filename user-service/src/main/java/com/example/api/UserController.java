package com.example.api;

import com.example.model.Role;
import com.example.model.UserDTO;
import com.example.model.UserJsonView;
import com.example.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @JsonView(UserJsonView.MainInfo.class)
    public ResponseEntity<UserDTO> addNewUser( @RequestBody UserDTO userDTO) {
        UserDTO newUserDTO = userService.addNewUser(userDTO);

        return ResponseEntity.ok(newUserDTO);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    @JsonView(UserJsonView.FullInfo.class)
    public ResponseEntity<List<UserDTO>> getAllUsers(@AuthenticationPrincipal Jwt jwt) {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user-info")
    @PreAuthorize("hasAuthority('USER')")
    @JsonView(UserJsonView.FullInfo.class)
    public ResponseEntity<UserDTO> getUserInfoByJwt(UserDTO userDTO) {
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/authorities")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<Set<Role>> getAuthoritiesByJwtToken(UserDTO userDTO) {
        return ResponseEntity.ok(userDTO.getSetOfAuthorities());
    }


}
