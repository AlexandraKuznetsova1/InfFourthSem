package com.itis.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PostDto {
    private Long id;
    private String createdAt;
    @NotBlank
    @Size(min = 3, max = 32)
    private String title;
    @NotBlank
    @Size(max = 1000)
    private String text;
}
