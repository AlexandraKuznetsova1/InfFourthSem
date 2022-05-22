package com.example.infsecondsemsemesterwork.dto;

import com.example.infsecondsemsemesterwork.models.Secret;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SecretDto {
	private Integer id;

	private String title;
	private String text;
	private String photoUrl;
	private String userNick;
	private LocalDateTime date;

	public static SecretDto from(Secret secret) {
		return SecretDto.builder()
				.id(secret.getId())
				.date(secret.getDate())
				.photoUrl(secret.getPhotoUrl())
				.text(secret.getText())
				.title(secret.getTitle())
				.userNick(secret.getUser().getNick())
				.build();
	}

	public static List<SecretDto> from(List<Secret> secrets) {
		return secrets.stream()
				.map(SecretDto::from)
				.collect(Collectors.toList());
	}
}
