package com.example.infsecondsemsemesterwork.services;

import com.example.infsecondsemsemesterwork.dto.UserDto;
import com.example.infsecondsemsemesterwork.dto.SignUpForm;
import com.example.infsecondsemsemesterwork.models.User;

import java.util.Optional;

public interface SignUpService {
	UserDto signUp(SignUpForm form);
	Optional<User> confirmUserByCode(String confirmCode);
}
