package ru.practicum.controller.priv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.event.*;
import ru.practicum.dto.request.EventRequestStatusUpdateResultDto;
import ru.practicum.dto.request.ParticipationRequestDto;
import ru.practicum.mapper.EventMapper;
import ru.practicum.mapper.RequestMapper;
import ru.practicum.service.event.EventService;
import ru.practicum.service.request.RequestService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Collection;

@Validated
@RestController
@RequestMapping("/users/{userId}/events")
@RequiredArgsConstructor
@Slf4j
public class PrivateEventController {

    private final EventService eventService;
    private final RequestService requestService;

    /**
     * Добавление нового события
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto create(
            @PathVariable @Min(0) long userId,
            @RequestBody @Valid NewEventDto dto) {
        log.info("Create {}", dto.toString());
        return EventMapper.toEventFullDto(
                eventService.create(userId, EventMapper.toEvent(dto)));
    }

    /**
     * Изменение события добавленного текущим пользователем
     */
    @PatchMapping("{eventId}")
    public EventFullDto updateEvent(
            @PathVariable @Min(0) long userId,
            @PathVariable @Min(0) long eventId,
            @Valid @RequestBody UpdateEventUserRequest dto) {
        log.info("Update by userId ={} and eventId={}, for {}", userId, eventId, dto.toString());
        return EventMapper.toEventFullDto(
                eventService.update(userId, eventId, EventMapper.toEvent(dto)));
    }

    /**
     * Изменение статуса (подтверждена, отменена) заявок на участие в событии текущего пользователя
     */
    @PatchMapping("{eventId}/requests")
    public EventRequestStatusUpdateResultDto updateEvent(
            @PathVariable @Min(0) long userId,
            @PathVariable @Min(0) long eventId,
            @Valid @RequestBody EventRequestStatusUpdateRequest requestStatusUpdate) {
        log.info("Update requests by userId ={}, eventId={}, for {}", userId, eventId, requestStatusUpdate.toString());
        return RequestMapper.toEventRequestStatusUpdateResultDto(
                requestService.updateStatus(userId, eventId, requestStatusUpdate));

    }

    /**
     * Получение событий, добавленных текущим пользователем
     */
    @GetMapping
    public Collection<EventShortDto> getAll(
            @PathVariable @Min(0) Long userId,
            @RequestParam(defaultValue = "0") @Min(0) int from,
            @RequestParam(defaultValue = "10") @Min(1) int size) {
        log.info("GET Events by userId={}, from={}, size={}", userId, from, size);
        return EventMapper.toEventShortDtoCollection(
                eventService.getAll(userId, from, size));
    }

    /**
     * Получение полной информации о событии добавленном текущим пользователем
     */
    @GetMapping("{eventId}")
    public EventFullDto getUserEvent(
            @PathVariable @Min(0) long userId,
            @PathVariable @Min(0) long eventId) {
        log.info("GET Events by userId={}, eventId={}", userId, eventId);
        return EventMapper.toEventFullDto(eventService.getUserEventById(eventId, userId));
    }

    /**
     * Получение информации о запросах на участие в событии текущего пользователя
     */
    @GetMapping("/{eventId}/requests")
    public Collection<ParticipationRequestDto> getUserEventRequests(
            @PathVariable @Min(0) long userId,
            @PathVariable @Min(0) long eventId) {
        return RequestMapper.toParticipationRequestDtoCollection(
                requestService.getEventRequests(userId, eventId));
    }

}
