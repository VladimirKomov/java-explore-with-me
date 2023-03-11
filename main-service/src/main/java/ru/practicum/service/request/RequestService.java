package ru.practicum.service.request;

import ru.practicum.dto.event.EventRequestStatusUpdateRequest;
import ru.practicum.dto.event.EventRequestStatusUpdateResult;
import ru.practicum.entity.Request;

import java.util.Collection;

public interface RequestService {

    Collection<Request> getAll(long userId);

    Request create(long userId, long eventId);

    Collection<Request> getEventRequests(long userId, long eventId);

    EventRequestStatusUpdateResult updateStatus(long userId, long eventId, EventRequestStatusUpdateRequest requestStatusUpdate);

    Request cancelRequest(long userId, long requestId);
}
