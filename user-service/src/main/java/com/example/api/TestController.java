package com.example.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    /***
     * Test
     * @param jwt - If using jwt token - you should use Jwt instead of UserDetails
     * @return - some response
     */
    @GetMapping
    public String test(@AuthenticationPrincipal Jwt jwt) {
        return "OK, " + jwt.getSubject();
    }

}
