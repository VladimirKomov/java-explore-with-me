package ru.practicum.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.mapper.EventMapper;
import ru.practicum.service.event.EventService;

import javax.validation.constraints.Min;
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
     * Возвращает полную информацию обо всех событиях подходящих под переданные условия
     */
    @GetMapping
    public Collection<EventFullDto> getAllByParameters(
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false) List<String> states,
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
