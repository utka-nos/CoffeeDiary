package com.example.api.coffee.controller;

import com.example.api.coffee.service.CoffeeService;
import com.example.dto.CoffeeDTO;
import com.example.jsonviews.CoffeeJSONView;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.JwtContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

//@SpringBootTest - поднимает ВЕСЬ контекст, не подходит нам, это не интеграционный тест
@WebMvcTest(controllers = CoffeeController.class)  // поднимает только, контекст связанный с указанным контроллером
//TODO: подумать как при добавлении этого импорта избавиться от проблемы с авторизацией jwt токена без обращения в сервис авторизации
@Import({JwtContext.class}) // импортируем настройкаи JWTConfig чтобы проверить авторизацию по jwt токену
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
    //@WithMockUser(authorities = {"USER"})
    void addNewCoffee() throws Exception {
        CoffeeDTO coffeeDTO = new CoffeeDTO();
        coffeeDTO.setName("brazil");

        String s = objectMapper.writer().writeValueAsString(coffeeDTO);

        coffeeDTO.setId(1L);

        String ex = objectMapper.writerWithView(CoffeeJSONView.Main.class).forType(CoffeeDTO.class).writeValueAsString(coffeeDTO);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/coffee")
                        .with(
                                SecurityMockMvcRequestPostProcessors
                                        .jwt()
                                        .authorities(
                                                List.of(new SimpleGrantedAuthority("USER"))
                                        )
                                        .jwt(jwt ->
                                                jwt.claim(StandardClaimNames.PREFERRED_USERNAME, "dima")
                                        )
                        )
                        .content(s).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.content().string(ex));

        //Mockito.verify(coffeeService, Mockito.times(1)).addNewCoffee(coffeeDTO);

    }

    @Test
    void getFullInfo() {
    }

    @Test
    void getAllCoffees() {
    }
}