package ru.practicum.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.entity.*;
import ru.practicum.exception.NotFoundException;
import ru.practicum.mapper.EventMapper;
import ru.practicum.repository.EventRepository;
import ru.practicum.repository.LocationRepository;
import ru.practicum.service.category.CategoryService;
import ru.practicum.service.user.UserService;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    public Event create(long userId, Event event) {
        User user = userService.getById(userId);
        Location location = getLocation(event.getLocation());
        Category category = categoryService.findById(event.getCategory().getId());
        event.setInitiator(user);
        event.setConfirmedRequests(0);
        event.setLocation(location);
        event.setCategory(category);
        event.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
        event.setPublishedOn(Timestamp.valueOf(LocalDateTime.now()));
        event.setState(State.PENDING);
        event.setViews(0);

        return eventRepository.save(event);
    }

    @Override
    public Event update(long userId, long eventId, Event donor) {
        Event recipient = getUserEventById(eventId, userId);
        recipient = updateEvent(donor, recipient);
        return eventRepository.save(recipient);
    }

    @Override
    public Collection<Event> getAll(long userId, int from, int size) {
        userService.getById(userId);
        return eventRepository.findAllByInitiatorId(userId, PageRequest.of(from, size));
    }

    @Override
    public Collection<Event> getAllByParameters(List<Long> users, List<String> states, List<Long> categories,
                                                Timestamp rangeStart, Timestamp rangeEnd, int from, int size) {
        if (rangeStart == null) rangeStart = Timestamp.valueOf(LocalDateTime.MAX);
        if (rangeEnd == null) rangeEnd = Timestamp.valueOf(LocalDateTime.MIN);
        return eventRepository.findByParameters(users, states, categories,
                rangeStart, rangeEnd, PageRequest.of(from, size));
    }

    @Override
    public Collection<Event> getAllByParametersPublic(String text, List<Long> categories, Boolean paid,
                                                      Timestamp rangeStart, Timestamp rangeEnd, Boolean onlyAvailable,
                                                      SortEvent sort, int from, int size) {
        if (rangeStart == null) rangeStart = Timestamp.valueOf(LocalDateTime.MAX);
        if (rangeEnd == null) rangeEnd = Timestamp.valueOf(LocalDateTime.MIN);
        return eventRepository.findByParametersForPublic(text, categories, paid,
                rangeStart, rangeEnd, onlyAvailable, PageRequest.of(from, size));
    }

    //+++ошибка, нужен эвент пользователя, а не любой
    @Override
    public Event getUserEventById(long eventId, long userId) {
        userService.getById(userId);
        return eventRepository.findById(eventId).orElseThrow(
                () -> new NotFoundException("Event with id=" + eventId));
    }

    @Override
    public Event getById(long eventId) {
        return eventRepository.findById(eventId).orElseThrow(
                () -> new NotFoundException("Event with id=" + eventId));
    }

    @Override
    public Event updateByAdmin(long eventId, Event donor) {
        Event recipient = getById(eventId);
        recipient = updateEvent(donor, recipient);
        return eventRepository.save(recipient);
    }

    private Event updateEvent(Event donor, Event recipient) {
        if (donor.getLocation() != null) {
            Location location = getLocation(donor.getLocation());
            donor.setLocation(location);
        }
        return EventMapper.updateEvent(donor, recipient);
    }

    private Location getLocation(Location location) {
        return locationRepository.findByLatAndLon(location.getLat(),
                location.getLon()).orElse(locationRepository.save(location));
    }
}
