package ru.practicum.dto.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserRequestDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    public String name;
}
