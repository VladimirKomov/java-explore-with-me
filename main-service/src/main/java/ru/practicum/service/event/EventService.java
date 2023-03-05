package ru.practicum.service.event;

import ru.practicum.dto.support.EventParameters;
import ru.practicum.entity.Event;

import java.util.Collection;

public interface EventService {

    Event create(EventParameters parameters, Event event);
    Event update(EventParameters parameters, Event donor);
    Collection<Event> getAll(Long userId, int from, int size);
}
