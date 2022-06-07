package com.itis.controllers;

import com.itis.dtos.SignUpDto;
import com.itis.dtos.UserDto;
import com.itis.services.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/signUp")
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;

    @PostMapping
    public UserDto signUp(@RequestBody SignUpDto signUpDto){
        return signUpService.signUp(signUpDto);
    }
}
