package com.example.infsecondsemsemesterwork.services;

import com.example.infsecondsemsemesterwork.dto.AddTipDto;
import com.example.infsecondsemsemesterwork.dto.TipDto;
import com.example.infsecondsemsemesterwork.dto.CommentDto;
import com.example.infsecondsemsemesterwork.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface TipService {
	List<TipDto> getAllTips();
	Optional<TipDto> createTip(MultipartFile pic, AddTipDto addTipDto, Integer userId);
	List<TipDto> getAllTipByTitle(String title);

	Optional<TipDto> getTipById(Integer tipId);

	Optional<UserDto> findUserByTipId(Integer tipId);

	List<CommentDto> getAllComments(Integer tipId);
}
