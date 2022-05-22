package com.example.infsecondsemsemesterwork.controllers;

import com.example.infsecondsemsemesterwork.dto.AddCommentDto;
import com.example.infsecondsemsemesterwork.dto.UserDto;
import com.example.infsecondsemsemesterwork.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.infsecondsemsemesterwork.dto.CommentDto;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

	private final CommentService commentService;

	@PostMapping("/tip/{tip-id}")
	public String savetipComment(@PathVariable("tip-id") Integer tipId,
	                               @RequestParam(value = "text",required = false) String text,
	                               HttpSession session) {
		AddCommentDto newData = AddCommentDto.builder()
				.topicId(tipId)
				.text(text)
				.userId(((UserDto)session.getAttribute("user")).getId())
				.build();

		Optional<CommentDto> tipComment = commentService.createTipComment(newData);

		return "redirect:/tips/" + tipId;
	}

	@PostMapping("/secret/{secret-id}")
	public String savesecretComment(@PathVariable("secret-id") Integer secretId,
	                               @RequestParam(value = "text",required = false) String text,
	                               HttpSession session) {
		AddCommentDto newData = AddCommentDto.builder()
				.topicId(secretId)
				.text(text)
				.userId(((UserDto)session.getAttribute("user")).getId())
				.build();

		Optional<CommentDto> secretCommentDto = commentService.createSecretComment(newData);

		return "redirect:/secrets/" + secretId;
	}
}
