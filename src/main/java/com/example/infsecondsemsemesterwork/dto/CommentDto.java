package com.example.infsecondsemsemesterwork.dto;

import lombok.Builder;
import lombok.Data;
import com.example.infsecondsemsemesterwork.models.TipComment;
import com.example.infsecondsemsemesterwork.models.SecretComment;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CommentDto {
	private Integer id;
	private String userAvatar;
	private String userNick;
	private Integer userId;
	private String text;

	public static CommentDto from(TipComment tipComment) {
		return CommentDto.builder()
				.id(tipComment.getId())
				.userId(tipComment.getUser().getId())
				.userAvatar(tipComment.getUser().getAvatarUrl())
				.userNick(tipComment.getUser().getNick())
				.text(tipComment.getText())
				.build();
	}

	public static CommentDto from(SecretComment secretComment) {
		return CommentDto.builder()
				.id(secretComment.getId())
				.userId(secretComment.getUser().getId())
				.userAvatar(secretComment.getUser().getAvatarUrl())
				.userNick(secretComment.getUser().getNick())
				.text(secretComment.getText())
				.build();
	}

	public static List<CommentDto> fromTipComments(List<TipComment> comments) {
		return comments.stream()
				.map(CommentDto::from)
				.collect(Collectors.toList());
	}

	public static List<CommentDto> fromSecretComments(List<SecretComment> comments) {
		return comments.stream()
				.map(CommentDto::from)
				.collect(Collectors.toList());
	}

}
