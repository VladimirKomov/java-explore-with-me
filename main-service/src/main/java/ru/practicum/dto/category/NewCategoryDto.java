package ru.practicum.dto.category;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewCategoryDto {
    @NotBlank
    private String name;
}
