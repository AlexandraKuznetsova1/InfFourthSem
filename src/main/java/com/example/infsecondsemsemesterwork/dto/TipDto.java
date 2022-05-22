package com.example.infsecondsemsemesterwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.infsecondsemsemesterwork.models.Tip;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipDto {
	private Integer id;

	private String title;
	private String text;
	private String photoUrl;
	private String userNick;
	private LocalDateTime date;

	public static TipDto from(Tip tip) {
		return TipDto.builder()
				.id(tip.getId())
				.date(tip.getDate())
				.photoUrl(tip.getPhotoUrl())
				.text(tip.getText())
				.title(tip.getTitle())
				.userNick(tip.getUser().getNick())
				.build();
	}

	public static List<TipDto> from(List<Tip> tips) {
		return tips.stream()
				.map(TipDto::from)
				.collect(Collectors.toList());
	}
}
