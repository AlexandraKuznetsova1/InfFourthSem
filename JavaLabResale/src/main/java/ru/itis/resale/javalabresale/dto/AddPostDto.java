package ru.itis.resale.javalabresale.dto;

import lombok.Data;

@Data
public class AddPostDto {
    private String title;
    private String text;
    private Long authorId;
}