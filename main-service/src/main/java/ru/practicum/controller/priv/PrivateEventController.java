package ru.practicum.controller.priv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.NewEventDto;
import ru.practicum.dto.support.EventParameters;
import ru.practicum.mapper.EventMapper;
import ru.practicum.mapper.LocationMapper;
import ru.practicum.service.event.EventService;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/users/{userId}/events")
@RequiredArgsConstructor
@Slf4j
public class PrivateEventController {

    private final EventService eventService;

    /**
     * Добавление нового события
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventFullDto create(@PathVariable @Min(1) Long userId,
                               @RequestBody @Valid NewEventDto newEventDto) {
        log.info("Create {}", newEventDto.toString());
        EventParameters parameters = EventParameters.builder()
                .userId(userId)
                .categoryId(newEventDto.getCategory())
                .location(LocationMapper.toLocation(newEventDto.getLocation()))
                .build();
        return EventMapper.toEventFullDto(
                eventService.create(parameters, EventMapper.toEvent(newEventDto)));
    }
}
