package com.example.infsecondsemsemesterwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpForm {
	private String nick;
	private String email;
	private String password;
	private String avatarUrl;
}
