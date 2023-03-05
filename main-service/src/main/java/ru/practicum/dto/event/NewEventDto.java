package ru.practicum.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.dto.location.LocationRequestDto;
import ru.practicum.validation.PlusTwoHours;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewEventDto {

    @Size(max = 2000, min = 20)
    @NotBlank
    private String annotation;
    @NotNull
    private Long category;
    @Size(max = 7000, min = 20)
    @NotBlank
    private String description;
    @PlusTwoHours(message = "Invalid date: cannot be less than two hours from now")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp eventDate;
    @NotNull
    private LocationRequestDto location;
    @Builder.Default
    private boolean paid = false;
    private int participantLimit;
    private boolean requestModeration;
    @Size(max = 120, min = 3)
    @NotBlank
    private String title;
}
