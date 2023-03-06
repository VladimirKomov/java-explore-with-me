package ru.practicum.service.request;

import ru.practicum.entity.Request;

import java.util.Collection;

public interface RequestService {

    Collection<Request> getAll(Long userId);
    Request create(Long userId, Long eventId);
}
