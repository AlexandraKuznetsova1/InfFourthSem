package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarEntryDto {
    private String model;
    private String carNumber;
    private String color;
    private Instant startTime;
    private Instant endTime;
    private Long userId;
    private Long parkingId;
}
