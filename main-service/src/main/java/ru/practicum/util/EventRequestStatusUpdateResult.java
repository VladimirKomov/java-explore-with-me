package ru.practicum.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.dto.request.ParticipationRequestDto;
import ru.practicum.entity.Request;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestStatusUpdateResult {

    List<Request> confirmedRequests = new ArrayList<Request>();
    List<Request> rejectedRequests = new ArrayList<Request>();
}
