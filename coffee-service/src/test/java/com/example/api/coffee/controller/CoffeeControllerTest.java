package com.example.api.coffee.controller;

import com.example.api.coffee.service.CoffeeService;
import com.example.dto.CoffeeDTO;
import com.example.jsonviews.CoffeeJSONView;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ActiveProfiles(profiles = {"test"})
@AutoConfigureMockMvc
class CoffeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CoffeeService coffeeService;

    @BeforeEach
    void setupCoffeeService() {
        CoffeeDTO coffeeDTO = new CoffeeDTO();
        coffeeDTO.setName("brazil");

        CoffeeDTO savedCoffeeDTO = new CoffeeDTO();
        savedCoffeeDTO.setName("brazil");
        savedCoffeeDTO.setId(1L);

        Mockito.when(coffeeService.addNewCoffee(coffeeDTO)).thenReturn(savedCoffeeDTO);
    }

    @Test
    @WithMockUser(authorities = {"USER"}, roles = "USER")
    void addNewCoffee() throws Exception {
        CoffeeDTO coffeeDTO = new CoffeeDTO();
        coffeeDTO.setName("brazil");

        String s = objectMapper.writer().writeValueAsString(coffeeDTO);

        coffeeDTO.setId(1L);

        String ex = objectMapper.writerWithView(CoffeeJSONView.Main.class).forType(CoffeeDTO.class).writeValueAsString(coffeeDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/coffee").content(s).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.content().string(ex));

    }

    @Test
    void getFullInfo() {
    }

    @Test
    void getAllCoffees() {
    }
}