/*
package com.itis.validation.validators;


import com.taxi.dto.SignUpDto;
import com.taxi.repositories.UserRepository;
import com.taxi.validation.annotations.EmailNotTaken;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class EmailNotTakenValidator implements ConstraintValidator<EmailNotTaken, SignUpDto> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(SignUpDto value, ConstraintValidatorContext context) {
        return !userRepository.existsByEmail(value.getEmail());
    }
}
*/
