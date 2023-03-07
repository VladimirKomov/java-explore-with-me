package ru.practicum.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import ru.practicum.dto.location.LocationRequestDto;

import java.sql.Timestamp;

import static ru.practicum.util.Constants.DATE_TIME;

@Data
@Builder
public class UpdateEventAdminRequest {
    private String annotation;
    private Long category;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME)
    private Timestamp eventDate;
    private LocationRequestDto location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    private String title;
}
