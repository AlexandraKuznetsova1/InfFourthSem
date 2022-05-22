package com.example.infsecondsemsemesterwork.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddCommentDto {
	private String text;
	private Integer userId;
	private Integer topicId;
}
