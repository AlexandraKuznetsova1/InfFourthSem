package com.example.infsecondsemsemesterwork.services.impl;

import com.example.infsecondsemsemesterwork.dto.CommentDto;
import com.example.infsecondsemsemesterwork.dto.SecretDto;
import com.example.infsecondsemsemesterwork.dto.UserDto;
import com.example.infsecondsemsemesterwork.models.Secret;
import com.example.infsecondsemsemesterwork.repositories.SecretCommentRepository;
import com.example.infsecondsemsemesterwork.repositories.SecretRepository;
import com.example.infsecondsemsemesterwork.services.SecretService;
import com.example.infsecondsemsemesterwork.util.CloudinaryHelper;
import com.example.infsecondsemsemesterwork.util.ImageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.infsecondsemsemesterwork.dto.AddSecretDto;
import com.example.infsecondsemsemesterwork.exceptions.UserNotExistsException;
import com.example.infsecondsemsemesterwork.models.User;
import com.example.infsecondsemsemesterwork.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecretServiceImpl implements SecretService {

	private final SecretRepository secretRepository;

	private final UserRepository userRepository;

	private final ImageHelper imageHelper;

	private final CloudinaryHelper cloudinaryHelper;

	private final SecretCommentRepository secretCommentRepository;

	@Override
	public List<SecretDto> getAllSecrets() {
		return SecretDto.from(secretRepository.findAll());
	}

	@Override
	public Optional<SecretDto> createSecret(MultipartFile pic, AddSecretDto addSecretDto, Integer userId) {
		String url = imageHelper.createUrl(pic);

		User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);

		Secret newSecret = Secret.builder()
				.date(LocalDateTime.now())
				.text(addSecretDto.getText())
				.title(addSecretDto.getTitle())
				.photoUrl(url)
				.user(user)
				.build();

		user.getSecrets().add(newSecret);

		Secret secret = secretRepository.save(newSecret);

		return Optional.of(SecretDto.from(newSecret));
	}

	@Override
	public List<SecretDto> getAllSecretsByTitle(String title) {
		return SecretDto.from(secretRepository.findAllByTitleContains(title));
	}

	@Override
	public Optional<SecretDto> getSecretById(Integer id) {
		Secret secret = secretRepository.getById(id);
		return Optional.of(SecretDto.from(secret));
	}

	@Override
	public Optional<UserDto> findUserBySecretId(Integer secretId) {
		Secret secret = secretRepository.getById(secretId);
		User user = userRepository.getById(secret.getUser().getId());
		return Optional.of(UserDto.from(user));
	}

	@Override
	public List<CommentDto> getAllComments(Integer secretId) {
		return CommentDto.fromSecretComments(secretCommentRepository.findAllBySecretId(secretId));
	}
}
