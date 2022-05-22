package com.example.infsecondsemsemesterwork.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.infsecondsemsemesterwork.dto.AddTipDto;
import com.example.infsecondsemsemesterwork.dto.UserDto;
import com.example.infsecondsemsemesterwork.services.TipService;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tips")
public class TipController {

	private final TipService tipService;

	@GetMapping
	public String gettipsPage() {
		return "alltips";
	}

	@PostMapping("/add")
	public String addsecret(@RequestParam("photo") MultipartFile pic, AddTipDto addTipDto, HttpSession session){
		UserDto user = (UserDto) session.getAttribute("user");
		tipService.createTip(pic, addTipDto, user.getId());
		return "redirect:/tips/add";
	}

	@GetMapping("/add")
	public String getAddsecretPage() {
		return "addtip";
	}

	@GetMapping("/{id}")
	public String gettipPage(@PathVariable("id") Integer tipId, Model model) {
		model.addAttribute("tip", tipService.getTipById(tipId).get());
		model.addAttribute("author", tipService.findUserByTipId(tipId).get());
		model.addAttribute("comments", tipService.getAllComments(tipId));
		return "tipInfo";
	}
}
