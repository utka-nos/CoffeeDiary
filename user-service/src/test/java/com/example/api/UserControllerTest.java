package com.example.api;

import com.example.UserDTO;
import com.example.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles(profiles = {"test"})
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setupUserServiceBehave() {
        Mockito.when(userService.getAllUsers())
                .thenReturn(List.of(
                        new UserDTO("dima", 1L),
                        new UserDTO("alina", 2L)
                ));
    }

    @Test
    void deleteUser() {
    }

    @Test
    @WithMockUser(authorities = {"USER", "ADMIN"})
    void getAllUsersWithAdminRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/all")).andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].username").value("alina"));
    }

    @Test
    @WithMockUser(authorities = {"USER"})
    void getAllUsersWithUserRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/all")).andExpect(status().is(403));
    }

    @Test
    @WithAnonymousUser
    void getAllUsersWithAnonymousUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/all")).andExpect(status().is(401));
    }

    @Test
    @WithAnonymousUser
    void addNewUser() throws Exception {
        UserDTO userToResponse = new UserDTO();

        userToResponse.setUsername("username");
        userToResponse.setPassword("1234");
        userToResponse.setEmail("some@email.com");
        userToResponse.setId(2L);

        Mockito.when(userService.addNewUser(Mockito.any())).thenReturn(userToResponse);

        UserDTO userToRequest = new UserDTO();
        userToRequest.setUsername("username");
        userToRequest.setPassword("1234");
        userToRequest.setEmail("some@email.com");

        String personJson = objectWriter.writeValueAsString(userToRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personJson))
                .andExpect(status().is(201))
                .andExpect(content().string("{\"id\":2,\"username\":\"username\"}"));
    }
}