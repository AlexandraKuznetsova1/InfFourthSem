package com.example.infsecondsemsemesterwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.infsecondsemsemesterwork.models.User;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto implements Serializable {
	private Integer id;

	private String nick;
	private String email;
	private String password;
	private String avatarUrl;
	private String confirmCode;

	private User.Role role;

	private User.State state;

	public static UserDto from(User user) {
		return UserDto.builder()
				.id(user.getId())
				.email(user.getEmail())
				.password(user.getPassword())
				.avatarUrl(user.getAvatarUrl())
				.nick(user.getNick())
				.role(user.getRole())
				.state(user.getState())
				.confirmCode(user.getConfirmCode())
				.build();
	}

	public static List<UserDto> from(List<User> users) {
		return users.stream()
				.map(UserDto::from)
				.collect(Collectors.toList());
	}
}
