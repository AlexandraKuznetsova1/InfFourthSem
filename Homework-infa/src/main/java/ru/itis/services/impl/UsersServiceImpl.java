package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dao.UserRepository;
import ru.itis.dto.SignInForm;
import ru.itis.dto.SignUpForm;
import ru.itis.dto.UserDto;
import ru.itis.exceptions.*;
import ru.itis.mappers.UserMapper;
import ru.itis.models.User;
import ru.itis.services.UsersService;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public UserDto signIn(SignInForm signInForm) {
        User user = userRepository.findByEmail(signInForm.getEmail())
                .orElseThrow(() -> new NotFoundException(ErrorEntity.INVALID_EMAIL));

        if(user.getHashPassword().equals(signInForm.getPassword())) {
            return userMapper.toResponse(user);
        } else {
            throw new IncorrectInput(ErrorEntity.INCORRECT_PASSWORD);
        }
    }

    @Transactional
    @Override
    public UserDto signUp(SignUpForm signUpForm) {
        userRepository.findByEmail(signUpForm.getEmail())
                .orElseThrow(() -> new AlreadyExistsException((ErrorEntity.EMAIL_ALREADY_TAKEN)));


        User user = userMapper.toRequest(signUpForm);
        user.setCreatedAt(Instant.now());

        userRepository.save(user);

        return userMapper.toResponse(user);
    }
}
