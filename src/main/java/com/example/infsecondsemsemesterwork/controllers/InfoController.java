package com.example.infsecondsemsemesterwork.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {
	@GetMapping(value = {"/", "/info"})
	public String getInfoPage() {
		return "info";
	}
}
