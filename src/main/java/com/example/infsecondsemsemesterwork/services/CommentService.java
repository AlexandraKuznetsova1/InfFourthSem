package com.example.infsecondsemsemesterwork.services;

import com.example.infsecondsemsemesterwork.dto.AddCommentDto;
import com.example.infsecondsemsemesterwork.dto.CommentDto;

import java.util.Optional;

public interface CommentService {
	Optional<CommentDto> createTipComment(AddCommentDto addCommentDto);

	Optional<CommentDto> createSecretComment(AddCommentDto newData);
}
