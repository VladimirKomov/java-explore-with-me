package ru.practicum.dto.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.practicum.dto.category.CategoryResponseDto;
import ru.practicum.dto.user.UserShortDto;
import ru.practicum.entity.Category;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static ru.practicum.util.Constants.DATE_TIME;

@Data
@Builder
@AllArgsConstructor
@NotNull
public class EventShortDto {

    public Long id;
    @NotNull
    @Size(min = 20, max = 2000)
    private String annotation;
    @NotNull
    private CategoryResponseDto category;
    private Integer confirmedRequests;
    @NotNull
    @JsonFormat(pattern = DATE_TIME)
    private Timestamp eventDate;
    private UserShortDto initiator;
    private Boolean paid;
    @NotNull
    @Size(min = 3, max = 120)
    private String title;
    private Integer views;
}
