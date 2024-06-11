package com.example.accapplication.controller;

import com.example.accapplication.dto.LoginResponseDto;
import com.example.accapplication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public String regitserUser(@RequestParam("username") String username, @RequestParam("password")String password){
        return authenticationService.registerUser(username,password);
    }

    @PostMapping("/login")
    public LoginResponseDto loginUser(@RequestParam("username") String username,@RequestParam("password")String password){
        return authenticationService.loginUser(username, password);
    }
}
