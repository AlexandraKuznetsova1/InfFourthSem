package com.itis.services;

import com.itis.dtos.SignUpDto;
import com.itis.dtos.UserDto;

public interface SignUpService {
    UserDto signUp(SignUpDto signUpDto);
}
