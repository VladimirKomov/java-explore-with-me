package ru.practicum.controller.priv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ParticipationRequestDto createRequest(@PathVariable @Min(1) Long userId,
                                                 @RequestParam @Min(1) Long eventId) {
        return RequestMapper.toParticipationRequestDto(requestService.create(userId, eventId));
    }

    /**
     * Получение информации о заявках текущего пользователя на участие в чужих событиях
     */
    @GetMapping
    public Collection<ParticipationRequestDto> getUserRequests(@PathVariable @Min(1) Long userId) {
        return RequestMapper.toParticipationRequestDtoCollection(requestService.getAll(userId));
    }
}
