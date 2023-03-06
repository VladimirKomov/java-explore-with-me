package ru.practicum.controller.priv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.dto.event.NewEventDto;
import ru.practicum.dto.event.UpdateEventUserRequest;
import ru.practicum.dto.support.EventParameters;
import ru.practicum.mapper.EventMapper;
import ru.practicum.mapper.LocationMapper;
import ru.practicum.service.event.EventService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.Collection;

@Validated
@RestController
@RequestMapping("/users/{userId}/events")
@RequiredArgsConstructor
@Slf4j
public class PrivateEventController {

    private final EventService eventService;

    //сначало делаем все контроллеры и валидацию

    /**
     * Добавление нового события
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto create(@PathVariable @Min(1) Long userId,
                               @RequestBody @Valid NewEventDto dto) {
        log.info("Create {}", dto.toString());
        return EventMapper.toEventFullDto(
                eventService.create(userId, EventMapper.toEvent(dto)));
    }

    /**
     * Обновление события
     */
    @PatchMapping("{eventId}")
    public EventFullDto updateEvent(@PathVariable @Min(1) Long userId,
                                    @PathVariable @Min(1) Long eventId,
                                    @Valid @RequestBody UpdateEventUserRequest dto) {
        log.info("Update by userId ={} and eventId={}, for {}", userId, eventId, dto.toString());
        return EventMapper.toEventFullDto(
                eventService.update(userId, eventId, EventMapper.toEvent(dto)));
    }

    /**
     * Получение событий, добавленных текущим пользователем
     */
    @GetMapping
    public Collection<EventShortDto> getAll(@PathVariable @Min(1) Long userId,
                                            @RequestParam(defaultValue = "0") @Min(0) int from,
                                            @RequestParam(defaultValue = "10") @Min(1) int size) {
        log.info("GET Events by userId={}, from={}, size={}", userId, from, size);
        return EventMapper.toEventShortDtoCollection(
                eventService.getAll(userId, from, size));

    }
}
