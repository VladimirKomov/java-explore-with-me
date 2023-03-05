package ru.practicum.dto.support;

import lombok.Builder;
import lombok.Data;
import ru.practicum.entity.Location;

@Data
@Builder
public class EventParameters {
    private Long userId;
    private Long categoryId;
    private Location location;
}
