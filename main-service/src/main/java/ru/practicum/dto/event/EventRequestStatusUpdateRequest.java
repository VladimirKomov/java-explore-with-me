package ru.practicum.dto.event;

import lombok.Data;
import ru.practicum.util.Status;

import java.util.Set;

@Data
public class EventRequestStatusUpdateRequest {

    Set<Long> requestIds;
    Status status;
}
