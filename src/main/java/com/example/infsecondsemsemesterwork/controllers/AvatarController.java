package com.example.infsecondsemsemesterwork.controllers;

import com.example.infsecondsemsemesterwork.dto.UserDto;
import com.example.infsecondsemsemesterwork.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class AvatarController {

	private final UserService userService;

	@PostMapping("/upload")
	public String updateAvatar(HttpSession session, @RequestParam("avatar") MultipartFile avatar) {
		UserDto user = (UserDto) session.getAttribute("user");
		UserDto userDto = userService.updateAvatar(avatar, user).get();
		session.setAttribute("user", userDto);
		return "redirect:/profile";
	}
}
