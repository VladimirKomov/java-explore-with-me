package ru.practicum.service.event;

import ru.practicum.dto.support.EventParameters;
import ru.practicum.entity.Event;

public interface EventService {

    Event create(EventParameters parameters, Event event);
}
