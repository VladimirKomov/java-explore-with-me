package ru.practicum.dto.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ToString
@NonNull
@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserRequestDto {
    @NotBlank
    public String name;
    @NotBlank
    @Email
    private String email;
}
