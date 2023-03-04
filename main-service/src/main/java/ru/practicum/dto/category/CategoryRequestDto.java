package ru.practicum.dto.category;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CategoryRequestDto {
    @NotBlank
    private String name;
}
