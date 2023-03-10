package ru.practicum.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.entity.State;
import ru.practicum.mapper.EventMapper;
import ru.practicum.service.event.EventService;
import ru.practicum.util.UpdateEventAdminRequest;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Validated
@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
@Slf4j
public class AdminEventController {

    private final EventService eventService;

    /**
     * Редактирование данных события и его статуса (отклонение/публикация)
     */
    @PatchMapping("{eventId}")
    public EventFullDto update(@PathVariable @Min(0) long eventId,
                               @Valid @RequestBody UpdateEventAdminRequest request) {
        return EventMapper.toEventFullDto(eventService.updateByAdmin(eventId,
                EventMapper.toEvent(request)));
    }

    /**
     * Возвращает полную информацию обо всех событиях подходящих под переданные условия
     */
    @GetMapping
    public Collection<EventFullDto> getAllByParameters(
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false) List<State> states,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) Timestamp rangeStart,
            @RequestParam(required = false) Timestamp rangeEnd,
            @RequestParam(defaultValue = "0", required = false) @Min(0) final int from,
            @RequestParam(defaultValue = "10", required = false) @Min(1) final int size) {
        log.info("GET events by users={}, states={}, categories={}, rangeStart={}, rangeEnd{}, from={}, size={}",
                users, states, categories, rangeStart, rangeEnd, from, size);
        return EventMapper.toEventFullDtoCollection(eventService.getAllByParameters(users, states, categories,
                rangeStart, rangeEnd, from, size));
    }
}
