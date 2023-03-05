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
public class UpdateEventUserRequest {

    @Size(max = 2000, min = 20)
    private String annotation;

    private Long category;
    @Size(max = 7000, min = 20)
    private String description;
    @PlusTwoHours(message = "Invalid date: cannot be less than two hours from now")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp eventDate;

    private LocationRequestDto location;
    private Boolean paid ;
    private Integer participantLimit;
    private Boolean requestModeration;
    private String stateAction;
    @Size(max = 120, min = 3)
    private String title;
}
