package ru.practicum.controller.priv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.request.ParticipationRequestDto;
import ru.practicum.mapper.RequestMapper;
import ru.practicum.service.request.RequestService;

import javax.validation.constraints.Min;
import java.util.Collection;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}/requests")
@Slf4j
public class PrivateRequestController {

    private final RequestService requestService;

    /**
     * Добавление запроса от текущего пользователя на участие в событии
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto createRequest(@PathVariable @Min(0) long userId,
                                                 @RequestParam @Min(0) long eventId) {
        log.info("Create request by userId={} for eventId={}", userId, eventId);
        return RequestMapper.toParticipationRequestDto(requestService.create(userId, eventId));
    }

    /**
     * Получение информации о заявках текущего пользователя на участие в чужих событиях
     */
    @GetMapping
    public Collection<ParticipationRequestDto> getUserRequests(@PathVariable @Min(0) long userId) {
        log.info("GET requests by userId={}", userId);
        return RequestMapper.toParticipationRequestDtoCollection(requestService.getAll(userId));
    }

    /**
     * Отмена своего запроса на участие в событии
     */
    @PatchMapping("{requestId}/cancel")
    public ParticipationRequestDto cancelRequest(@PathVariable @Min(0) long userId,
                                                 @PathVariable @Min(0) long requestId) {
        log.info("Patch requests by userId={} for requestId={}", userId, requestId);
        return RequestMapper.toParticipationRequestDto(requestService.cancelRequest(userId, requestId));
    }

}
