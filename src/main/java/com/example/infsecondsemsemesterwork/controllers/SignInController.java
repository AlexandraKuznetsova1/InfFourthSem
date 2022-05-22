package com.example.infsecondsemsemesterwork.controllers;

import com.example.infsecondsemsemesterwork.dto.UserDto;
import com.example.infsecondsemsemesterwork.security.VkAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.infsecondsemsemesterwork.models.User;
import com.example.infsecondsemsemesterwork.security.details.CustomUserDetails;
import com.example.infsecondsemsemesterwork.services.UserService;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signIn")
public class SignInController {

	private final UserService userService;

	@GetMapping
	public String getSignInPage(Authentication authentication, @RequestParam(value = "reason", required = false) String reason) {
		if (authentication != null) {
			return "redirect:/profile";
		}

		if (reason != null && reason.equals("error")) {
			return "signInFailed";
		}

		return "signIn";
	}

	@GetMapping("/vk")
	public String loginWithVk(HttpSession session, @RequestParam("first_name") String firstName,
	                          @RequestParam("last_name") String lastName,
	                          @RequestParam("uid") String id,
	                          @RequestParam("photo") String avatarUrl) {

		User user = User.builder()
				.nick(firstName + " " + lastName)
				.role(User.Role.USER)
				.state(User.State.CONFIRMED)
				.avatarUrl(avatarUrl)
				.confirmCode(id)
				.tipComments(Collections.emptySet())
				.secretComments(Collections.emptySet())
				.tips(Collections.emptySet())
				.secrets(Collections.emptySet())
				.email("don't know")
				.build();

		Authentication auth = new VkAuth(new CustomUserDetails(user));
		SecurityContextHolder.getContext().setAuthentication(auth);

		Optional<UserDto> userDto = userService.saveUser(user);
		session.setAttribute("user", userDto.get());

		return "redirect:/profile";
	}

}
