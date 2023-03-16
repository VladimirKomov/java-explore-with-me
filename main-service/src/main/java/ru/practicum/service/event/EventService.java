package ru.practicum.service.event;

import ru.practicum.entity.Event;
import ru.practicum.entity.SortEvent;
import ru.practicum.entity.State;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EventService {

    Event create(long userId, Event event);

    Event save(Event event);

    Event update(long userId, long eventId, Event donor);

    Collection<Event> getAll(long userId, int from, int size);

    Collection<Event> getAll(Collection<Long> eventIds);

    Collection<Event> getAllByParameters(List<Long> users, List<State> states, List<Long> categories,
                                         Timestamp rangeStart, Timestamp rangeEnd, int from, int size);

    Collection<Event> getAllByParametersPublic(String text, List<Long> categories, Boolean paid, Timestamp rangeStart,
                                               Timestamp rangeEnd, Boolean onlyAvailable, SortEvent sort,
                                               int from, int size);

    Event getUserEventById(long eventId, long userId);

    Event getById(long eventId);

    Event getByIdForPublic(long eventId);

    Event updateByAdmin(long eventId, Event event);

    Optional<Event> getByIdForRequest(long eventId);
}
