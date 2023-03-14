package ru.practicum.dto.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.dto.user.UserShortDto;

import java.sql.Timestamp;

import static ru.practicum.util.Constants.DATE_TIME;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {

    private long id;
    private UserShortDto author;
    private EventShortDto event;
    @JsonFormat(pattern = DATE_TIME)
    private Timestamp createdOn;
    @JsonFormat(pattern = DATE_TIME)
    private Timestamp updatedOn;
    private String text;

}
