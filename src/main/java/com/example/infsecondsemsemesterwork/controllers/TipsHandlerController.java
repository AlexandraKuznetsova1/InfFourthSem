package com.example.infsecondsemsemesterwork.controllers;

import com.example.infsecondsemsemesterwork.dto.TipDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.infsecondsemsemesterwork.services.TipService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/handletips")
public class TipsHandlerController {
	private final TipService tipService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<TipDto> getAlltips() {
		return tipService.getAllTips();
	}

	@GetMapping("/{title}")
	@ResponseStatus(HttpStatus.OK)
	public List<TipDto> getsecretsByTitle(@PathVariable String title) {
		return tipService.getAllTipByTitle(title);
	}
}
