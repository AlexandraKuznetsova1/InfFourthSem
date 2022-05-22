package com.example.infsecondsemsemesterwork.controllers;

import com.example.infsecondsemsemesterwork.dto.UserDto;
import com.example.infsecondsemsemesterwork.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/checkSignUp")
public class CheckSignUpController {
	private final UserService userService;

	@GetMapping("/email/{email}")
	public String checkEmail(@PathVariable("email") String email) {
		Optional<UserDto> userByEmail = userService.findUserByEmail(email);

		if (userByEmail.isPresent()) {
			return "email taken";
		}

		return "ok";
	}

	@GetMapping("/nick/{nick}")
	public String checkNick(@PathVariable("nick") String nick) {
		Optional<UserDto> userByNick = userService.findUserByNick(nick);

		if (userByNick.isPresent()) {
			return "nick taken";
		}

		return "ok";
	}
}
