package com.example.infsecondsemsemesterwork.controllers;

import com.example.infsecondsemsemesterwork.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	@GetMapping
	public String getUsersPage() {
		return "allUsers";
	}

	@GetMapping("/{id}")
	public String getUserPage(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("detailUser", userService.getUserById(id).get());
		return "userInfo";
	}
}
