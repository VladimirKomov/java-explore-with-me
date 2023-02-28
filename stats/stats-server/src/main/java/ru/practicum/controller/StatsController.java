package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.entity.EndpointHit;
import ru.practicum.dto.EndpointHitRequestDto;
import ru.practicum.mapper.StatsMapper;
import ru.practicum.service.StatsService;

@RestController
@Validated
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    @PostMapping("/hit")
    public EndpointHit create(EndpointHitRequestDto endpointHitRequestDto) {
        return statsService.createHit(StatsMapper.toEndpointHit(endpointHitRequestDto));
    }

}
