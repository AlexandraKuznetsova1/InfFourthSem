package ru.itis.services;


import ru.itis.dto.SignInForm;
import ru.itis.dto.SignUpForm;
import ru.itis.dto.UserDto;

public interface UsersService {
    UserDto signIn(SignInForm signInForm);
    UserDto signUp(SignUpForm signUpForm);
}
