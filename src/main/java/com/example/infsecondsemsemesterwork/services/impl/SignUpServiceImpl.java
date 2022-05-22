package com.example.infsecondsemsemesterwork.services.impl;

import com.example.infsecondsemsemesterwork.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.infsecondsemsemesterwork.dto.SignUpForm;
import com.example.infsecondsemsemesterwork.exceptions.UserAlreadyExistsException;
import com.example.infsecondsemsemesterwork.models.User;
import com.example.infsecondsemsemesterwork.repositories.UserRepository;
import com.example.infsecondsemsemesterwork.services.SignUpService;
import com.example.infsecondsemsemesterwork.util.EmailUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class SignUpServiceImpl implements SignUpService {

	private final static String DEFAULT_AVATAR_URL = "https://res.cloudinary.com/sasha29/image/upload/v1653229308/cld-sample.jpg";

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final EmailUtil emailUtil;

	@Value("${project.base.url}")
	private String BASE_URL;

	@Override
	public UserDto signUp(SignUpForm form) {
		if (userRepository.findByEmail(form.getEmail()).isPresent() ||
				userRepository.findByNick(form.getNick()).isPresent()) {
			throw new UserAlreadyExistsException();
		}

		User user = User.builder()
				.email(form.getEmail())
				.nick(form.getNick())
				.avatarUrl(DEFAULT_AVATAR_URL)
				.password(passwordEncoder.encode(form.getPassword()))
				.role(User.Role.USER)
				.state(User.State.NOT_CONFIRMED)
				.confirmCode(UUID.randomUUID().toString())
				.build();

		User savedUser = userRepository.save(user);

		Map<String, String> data = new HashMap<>();
		data.put("name", user.getNick());
		data.put("link", "http://" + BASE_URL + "/signUp/confirm/" + user.getConfirmCode());
		emailUtil.sendMail(user.getEmail(), "confirm", "confirm_email.ftlh", data);

		return UserDto.from(savedUser);
	}

	@Override
	public Optional<User> confirmUserByCode(String confirmCode) {
		Optional<User> user = userRepository.findByConfirmCodeLike(confirmCode);

		if (user.isPresent()) {
			if (user.get().getState().equals(User.State.NOT_CONFIRMED)) {
				user.get().setState(User.State.CONFIRMED);
				userRepository.save(user.get());
				return user;
			} else {
				return Optional.empty();
			}
		} else {
			return Optional.empty();
		}
	}
}
