package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dto.SignInForm;
import ru.itis.dto.SignUpForm;
import ru.itis.dto.UserDto;
import ru.itis.exceptions.AlreadyExistsException;
import ru.itis.exceptions.NotFoundException;
import ru.itis.services.UsersService;

@RequiredArgsConstructor
@RestController
public class UsersController {

    private final UsersService usersService;

    @PostMapping(value = "/sign-up")
    public UserDto signUp(@RequestBody SignUpForm signUpForm) {
        try {
            String hashPassword = String.valueOf(signUpForm.getPassword().hashCode());
            signUpForm.setPassword(hashPassword);
            return usersService.signUp(signUpForm);
        } catch (AlreadyExistsException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(value = "/sign-in")
    public UserDto signIn(@RequestBody SignInForm signInForm) {
        try {
            String hashPassword = String.valueOf(signInForm.getPassword().hashCode());
            signInForm.setPassword(hashPassword);
            return usersService.signIn(signInForm);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
