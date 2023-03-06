package ru.practicum.service.event;

import ru.practicum.entity.Event;
import ru.practicum.entity.SortEvent;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

public interface EventService {

    Event create(Long userId, Event event);

    Event update(Long userId, Long eventId, Event donor);

    Collection<Event> getAll(Long userId, int from, int size);

    Collection<Event> getAllByParameters(List<Long> users, List<String> states, List<Long> categories,
                                         Timestamp rangeStart, Timestamp rangeEnd, int from, int size);

    Collection<Event> getAllByParametersPublic(String text, List<Long> categories, Boolean paid, Timestamp rangeStart, Timestamp rangeEnd, Boolean onlyAvailable, SortEvent sort, int from, int size);
}
