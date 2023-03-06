package ru.practicum.dto.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.practicum.entity.Status;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static ru.practicum.dto.support.Constants.DATE_TIME;

@Data
@Builder
public class ParticipationRequestDto {
    private Long id;
    @NotNull
    private Long event;
    @NotNull
    private Long requester;
    private Status status;
    @DateTimeFormat(pattern = DATE_TIME)
    private LocalDateTime created;
}
