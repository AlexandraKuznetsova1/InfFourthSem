package com.example.infsecondsemsemesterwork.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.infsecondsemsemesterwork.dto.AddSecretDto;
import com.example.infsecondsemsemesterwork.dto.UserDto;
import com.example.infsecondsemsemesterwork.services.SecretService;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/secrets")
public class SecretController {

	private final SecretService secretService;

	@GetMapping
	public String getsecretsPage() {
		return "allsecrets";
	}

	@PostMapping("/add")
	public String addsecret(@RequestParam("photo") MultipartFile pic, AddSecretDto addSecretDto, HttpSession session){
		UserDto user = (UserDto) session.getAttribute("user");
		secretService.createSecret(pic, addSecretDto, user.getId());
		return "redirect:/secrets/add";
	}

	@GetMapping("/add")
	public String getAddsecretPage() {
		return "addsecret";
	}

	@GetMapping("/{id}")
	public String getsecretPage(@PathVariable("id") Integer secretId, Model model) {
		model.addAttribute("secret", secretService.getSecretById(secretId).get());
		model.addAttribute("author", secretService.findUserBySecretId(secretId).get());
		model.addAttribute("comments", secretService.getAllComments(secretId));
		return "secretInfo";
	}
}
