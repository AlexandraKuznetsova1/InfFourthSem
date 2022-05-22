package com.example.infsecondsemsemesterwork.services.impl;

import com.example.infsecondsemsemesterwork.models.*;
import com.example.infsecondsemsemesterwork.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.infsecondsemsemesterwork.dto.AddCommentDto;
import com.example.infsecondsemsemesterwork.dto.CommentDto;
import com.example.infsecondsemsemesterwork.services.CommentService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	private final UserRepository userRepository;

	private final TipRepository tipRepository;

	private final SecretRepository secretRepository;

	private final TipCommentRepository tipCommentRepository;

	private final SecretCommentRepository secretCommentRepository;

	@Override
	public Optional<CommentDto> createTipComment(AddCommentDto addCommentDto) {
		User user = userRepository.getById(addCommentDto.getUserId());
		Tip tip = tipRepository.getById(addCommentDto.getTopicId());

		TipComment comment = TipComment.builder()
				.text(addCommentDto.getText())
				.tip(tip)
				.user(user)
				.build();

		user.getTipComments().add(comment);
		tip.getTipComment().add(comment);

		TipComment savedComment = tipCommentRepository.save(comment);

		return Optional.of(CommentDto.from(savedComment));
	}

	@Override
	public Optional<CommentDto> createSecretComment(AddCommentDto newData) {
		User user = userRepository.getById(newData.getUserId());
		Secret secret = secretRepository.getById(newData.getTopicId());

		SecretComment comment = SecretComment.builder()
				.text(newData.getText())
				.secret(secret)
				.user(user)
				.build();

		user.getSecretComments().add(comment);
		secret.getSecretComment().add(comment);

		SecretComment savedComment = secretCommentRepository.save(comment);

		return Optional.of(CommentDto.from(savedComment));
	}
}
