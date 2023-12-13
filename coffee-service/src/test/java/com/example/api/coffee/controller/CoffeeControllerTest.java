package com.example.api.coffee.controller;

import com.example.api.coffee.service.CoffeeService;
import com.example.config.CoffeeServiceSecurityConfig;
import com.example.dto.CoffeeDTO;
import com.example.exceptions.CoffeeNotFoundException;
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
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

//@SpringBootTest - поднимает ВЕСЬ контекст, не подходит нам, это не интеграционный тест
@WebMvcTest(controllers = CoffeeController.class)  // поднимает только, контекст связанный с указанным контроллером
@Import({JwtContext.class, CoffeeServiceSecurityConfig.class}) // импортируем настройкаи JWTConfig чтобы проверить авторизацию по jwt токену
@ActiveProfiles(profiles = {"test"})
@AutoConfigureMockMvc
class CoffeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CoffeeService coffeeService;

    @MockBean
    private JwtDecoder jwtDecoder;

    private final CoffeeDTO coffeeDTO      = new CoffeeDTO();
    private final CoffeeDTO savedCoffeeDTO = new CoffeeDTO();

    @BeforeEach
    void setupCoffeeService() {
        coffeeDTO.setName("brazil");

        savedCoffeeDTO.setName("brazil");
        savedCoffeeDTO.setId(1L);

        Mockito.when(coffeeService.addNewCoffee(coffeeDTO)).thenReturn(savedCoffeeDTO);
        Mockito.when(coffeeService.getCoffeeById(1L)).thenReturn(savedCoffeeDTO);
        Mockito.when(coffeeService.getCoffeeById(100L)).thenThrow(new CoffeeNotFoundException());
    }

    @Test
    void addNewCoffeeSuccess() throws Exception {
        String coffeeDTOToSave = objectMapper.writer().writeValueAsString(coffeeDTO);

        String expected = objectMapper.writerWithView(CoffeeJSONView.Main.class).forType(CoffeeDTO.class).writeValueAsString(savedCoffeeDTO);
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
                        //.with(SecurityMockMvcRequestPostProcessors.csrf())
                        .content(coffeeDTOToSave).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.content().string(expected));

        Mockito.verify(coffeeService, Mockito.times(1)).addNewCoffee(coffeeDTO);
    }

    @Test
    void addNewCoffeeWithoutUserRole() throws Exception {
        String coffeeDTOToSave = objectMapper.writer().writeValueAsString(coffeeDTO);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/coffee")
                                .with(
                                        SecurityMockMvcRequestPostProcessors
                                                .jwt()
                                                .authorities(
                                                        List.of(new SimpleGrantedAuthority("ADMIN"))
                                                )
                                                .jwt(jwt ->
                                                        jwt.claim(StandardClaimNames.PREFERRED_USERNAME, "dima")
                                                )
                                )
                                .with(SecurityMockMvcRequestPostProcessors.csrf())
                                .content(coffeeDTOToSave).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(403));

        Mockito.verify(coffeeService, Mockito.times(0)).addNewCoffee(coffeeDTO);
    }

    @Test
    void addNewCoffeeWithoutJwtToken() throws Exception {
        String coffeeDtoStr = objectMapper.writer().writeValueAsString(coffeeDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/coffee")
                .content(coffeeDtoStr)
                        .with(SecurityMockMvcRequestPostProcessors.anonymous())
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().is(401));
    }

    @Test
    void getFullInfo_withUserRole() throws Exception {
        String expected = objectMapper.writerWithView(CoffeeJSONView.Full.class).forType(CoffeeDTO.class).writeValueAsString(savedCoffeeDTO);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/coffee/1")
                .with(SecurityMockMvcRequestPostProcessors
                        .jwt()
                        .authorities(new SimpleGrantedAuthority("USER"))
                ))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(expected));
    }

    @Test
    void getFullInfo_withAdminRole() throws Exception {
        String expected = objectMapper.writerWithView(CoffeeJSONView.Full.class).forType(CoffeeDTO.class).writeValueAsString(savedCoffeeDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/coffee/1")
                        .with(SecurityMockMvcRequestPostProcessors
                                .jwt()
                                .authorities(new SimpleGrantedAuthority("ADMIN"))
                        ))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string(expected));
    }

    @Test
    void getFullInfo_withAnonymousUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/coffee/1")
                        .with(SecurityMockMvcRequestPostProcessors
                                .anonymous()
                        ))
                .andExpect(MockMvcResultMatchers.status().is(401));
    }

    @Test
    void getFullInfo_withInvalidCoffeeId() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/coffee/100").with(
                        SecurityMockMvcRequestPostProcessors.jwt().authorities(new SimpleGrantedAuthority("USER"))
                )
        ).andExpect(MockMvcResultMatchers.status().is(404));

        Mockito.verify(coffeeService, Mockito.times(1)).getCoffeeById(100L);
    }

    @Test
    void getAllCoffees() {
    }
}