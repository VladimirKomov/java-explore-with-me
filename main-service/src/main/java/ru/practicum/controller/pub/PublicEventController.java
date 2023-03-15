package ru.practicum.controller.pub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.StatsClient;
import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.entity.SortEvent;
import ru.practicum.mapper.EventMapper;
import ru.practicum.service.event.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Validated
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Slf4j
public class PublicEventController {

    private final EventService eventService;
    private final StatsClient statsClient;


    /**
     * Получение событий с возможностью фильтрации
     */
    @GetMapping
    public Collection<EventShortDto> getFilteredEvents(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) Boolean paid,
            @RequestParam(required = false) Timestamp rangeStart,
            @RequestParam(required = false) Timestamp rangeEnd,
            @RequestParam(defaultValue = "false") Boolean onlyAvailable,
            @RequestParam(required = false) SortEvent sort,
            @RequestParam(defaultValue = "0") @Min(0) int from,
            @RequestParam(defaultValue = "10") @Min(1) int size, HttpServletRequest request) {
        log.info("GET events by text={}, categories={}, paid={}, rangeStart={}, rangeEnd{}, " +
                        "onlyAvailable={}, sort={}, from={}, size={}",
                text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
        statsClient.createHit(request);
        return EventMapper.toEventShortDtoCollection(
                eventService.getAllByParametersPublic(text, categories, paid, rangeStart,
                        rangeEnd, onlyAvailable, sort, from, size));
    }

    /**
     * Получение события по его идентификатору
     */
    @GetMapping("{id}")
    public EventFullDto getById(
            @PathVariable @Min(0) long id, HttpServletRequest request) {
        log.info("GET event by id={}", id);
        statsClient.createHit(request);
        return EventMapper.toEventFullDto(
                eventService.getByIdForPublic(id));
    }


}
