package com.example.infsecondsemsemesterwork.services.impl;

import com.example.infsecondsemsemesterwork.dto.AddTipDto;
import com.example.infsecondsemsemesterwork.dto.TipDto;
import com.example.infsecondsemsemesterwork.dto.CommentDto;
import com.example.infsecondsemsemesterwork.dto.UserDto;
import com.example.infsecondsemsemesterwork.repositories.TipCommentRepository;
import com.example.infsecondsemsemesterwork.util.CloudinaryHelper;
import com.example.infsecondsemsemesterwork.util.ImageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.infsecondsemsemesterwork.exceptions.UserNotExistsException;
import com.example.infsecondsemsemesterwork.models.Tip;
import com.example.infsecondsemsemesterwork.models.User;
import com.example.infsecondsemsemesterwork.repositories.TipRepository;
import com.example.infsecondsemsemesterwork.repositories.UserRepository;
import com.example.infsecondsemsemesterwork.services.TipService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TipServiceImpl implements TipService {

	private final TipRepository tipRepository;

	private final UserRepository userRepository;

	private final ImageHelper imageHelper;

	private final CloudinaryHelper cloudinaryHelper;

	private final TipCommentRepository tipCommentRepository;

	@Override
	public List<TipDto> getAllTips() {
		return TipDto.from(tipRepository.findAll());
	}

	@Override
	public Optional<TipDto> createTip(MultipartFile pic, AddTipDto addTipDto, Integer userId) {
		String url = imageHelper.createUrl(pic);

		User user = userRepository.findById(userId).orElseThrow(UserNotExistsException::new);

		Tip newTip = Tip.builder()
				.date(LocalDateTime.now())
				.text(addTipDto.getText())
				.title(addTipDto.getTitle())
				.photoUrl(url)
				.user(user)
				.build();

		user.getTips().add(newTip);

		Tip tip = tipRepository.save(newTip);

		return Optional.of(TipDto.from(newTip));
	}

	@Override
	public List<TipDto> getAllTipByTitle(String title) {
		return TipDto.from(tipRepository.findAllByTitleContains(title));
	}

	@Override
	public Optional<TipDto> getTipById(Integer tipId) {
		return Optional.of(TipDto.from(tipRepository.getById(tipId)));
	}

	@Override
	public Optional<UserDto> findUserByTipId(Integer tipId) {
		Tip tip = tipRepository.getById(tipId);
		User user = userRepository.getById(tip.getUser().getId());
		return Optional.of(UserDto.from(user));
	}

	@Override
	public List<CommentDto> getAllComments(Integer tipId) {
		return CommentDto.fromTipComments(tipCommentRepository.findAllByTipId(tipId));
	}
}
