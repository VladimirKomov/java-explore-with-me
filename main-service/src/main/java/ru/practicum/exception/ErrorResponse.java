package ru.practicum.exception;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {
    private final Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
    private String status;
    private String reason;
    private String message;
}
