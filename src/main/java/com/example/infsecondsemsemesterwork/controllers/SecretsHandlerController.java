package com.example.infsecondsemsemesterwork.controllers;

import com.example.infsecondsemsemesterwork.dto.SecretDto;
import com.example.infsecondsemsemesterwork.services.SecretService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/handlesecrets")
public class SecretsHandlerController {

	private final SecretService secretService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<SecretDto> getAllsecrets() {
		return secretService.getAllSecrets();
	}

	@GetMapping("/{title}")
	@ResponseStatus(HttpStatus.OK)
	public List<SecretDto> getsecretsByTitle(@PathVariable String title) {
		return secretService.getAllSecretsByTitle(title);
	}
}
