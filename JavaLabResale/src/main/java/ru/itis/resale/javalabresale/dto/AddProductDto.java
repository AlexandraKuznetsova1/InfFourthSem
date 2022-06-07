package ru.itis.resale.javalabresale.dto;
import lombok.Data;

@Data
public class AddProductDto {
    private String title;
    private String description;
    private String price;
    private Long authorId;
}
