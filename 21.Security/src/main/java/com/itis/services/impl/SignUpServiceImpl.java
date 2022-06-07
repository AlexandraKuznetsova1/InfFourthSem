package com.itis.services.impl;

import com.itis.dtos.DtoMapper;
import com.itis.dtos.SignUpDto;
import com.itis.dtos.UserDto;
import com.itis.models.User;
import com.itis.respositories.UserRepository;
import com.itis.services.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {
    private final UserRepository userRepository;
    private final DtoMapper dtoMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto signUp(SignUpDto signUpDto) {
        User user = dtoMapper.signUpDtoToUser(signUpDto);
        user.setHashedPassword(passwordEncoder.encode(signUpDto.getPassword()));
        return dtoMapper.userToUserDto(userRepository.save(user));
    }
}
