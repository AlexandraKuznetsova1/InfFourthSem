package ru.itis.resale.javalabresale.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.resale.javalabresale.models.Post;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private Long id;
    private String createdAt;
    private String title;
    private String text;

    public static PostDto from(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .createdAt(post.getCreatedAt().toString())
                .text(post.getText())
                .title(post.getTitle())
                .build();
    }

    public static List<PostDto> from(List<Post> posts) {
        return posts.stream().map(PostDto::from).collect(Collectors.toList());
    }
}
