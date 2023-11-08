package com.example.api;

import com.example.model.UserDTO;
import com.example.service.UserService;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(profiles = {"test"})
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    private void setupUserServiceBehave() {
        Mockito.when(userService.getAllUsers())
                .thenReturn(List.of(
                        new UserDTO("dima", 1L),
                        new UserDTO("alina", 2L)
                ));
    }

    @Test
    void addNewUser() {

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
}