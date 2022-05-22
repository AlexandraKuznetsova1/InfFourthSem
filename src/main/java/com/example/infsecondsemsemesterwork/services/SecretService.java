package com.example.infsecondsemsemesterwork.services;

import com.example.infsecondsemsemesterwork.dto.CommentDto;
import com.example.infsecondsemsemesterwork.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;
import com.example.infsecondsemsemesterwork.dto.AddSecretDto;
import com.example.infsecondsemsemesterwork.dto.SecretDto;

import java.util.List;
import java.util.Optional;

public interface SecretService {
	List<SecretDto> getAllSecrets();
	Optional<SecretDto> createSecret(MultipartFile pic, AddSecretDto addSecretDto, Integer userId);
	List<SecretDto> getAllSecretsByTitle(String title);
	Optional<SecretDto> getSecretById(Integer id);
	Optional<UserDto> findUserBySecretId(Integer secretId);

	List<CommentDto> getAllComments(Integer secretId);
}
