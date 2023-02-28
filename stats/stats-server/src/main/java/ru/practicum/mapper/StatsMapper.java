package ru.practicum.mapper;

import ru.practicum.dto.EndpointHitRequestDto;
import ru.practicum.entity.EndpointHit;

public class StatsMapper {

    public static EndpointHit toEndpointHit(EndpointHitRequestDto dto) {

        return EndpointHit.builder()
                .app(dto.getApp())
                .uri(dto.getUri())
                .ip(dto.getIp())
                .timestamp(dto.getTimestamp())
                .build();
    }
}
