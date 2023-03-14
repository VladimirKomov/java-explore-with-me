package ru.practicum.controller.pub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.comment.CommentResponseDto;
import ru.practicum.entity.SortComment;
import ru.practicum.mapper.CommentMapper;
import ru.practicum.service.comment.CommentService;

import javax.validation.constraints.Min;
import java.util.Collection;

@RestController
@RequestMapping("/comments")
@Validated
@RequiredArgsConstructor
@Slf4j
public class PublicCommentController {

    private final CommentService commentService;

    /**
     * Получение комментария по его идентификатору
     */
    @GetMapping("{commentId}")
    public CommentResponseDto getById(
            @PathVariable @Min(0) long commentId) {
        log.info("GET comment by id={}", commentId);

        return CommentMapper.toCommentResponseDto(
                commentService.getById(commentId));
    }

    /**
     * Получение списка комментариев события
     */
    @GetMapping("{eventId}/events")
    public Collection<CommentResponseDto> getAllByEventId(
            @PathVariable @Min(0) long eventId,
            @RequestParam(defaultValue = "DESC") SortComment sort,
            @RequestParam(defaultValue = "0", required = false) @Min(0) int from,
            @RequestParam(defaultValue = "10", required = false) @Min(1) int size) {
        log.info("GET comments eventId={}, from={}, size={}", eventId, from, size);
        return CommentMapper.toCommentResponseDtoCollection(
                commentService.getByEventId(eventId, sort, from, size));
    }

    /**
     * Получение списка комментариев пользователя
     */
    @GetMapping("{userId}/users")
    public Collection<CommentResponseDto> getAllByUsertId(
            @PathVariable @Min(0) long userId,
            @RequestParam(defaultValue = "DESC") SortComment sort,
            @RequestParam(defaultValue = "0", required = false) @Min(0) int from,
            @RequestParam(defaultValue = "10", required = false) @Min(1) int size) {
        log.info("GET comments userId={}, from={}, size={}", userId, from, size);
        return CommentMapper.toCommentResponseDtoCollection(
                commentService.getByUserId(userId, sort, from, size));
    }

    /**
     * Получение списка комментариев пользователя по событию
     */
    @GetMapping("{userId}/users/{eventId}/events")
    public Collection<CommentResponseDto> getByUserIdEventId(
            @PathVariable @Min(0) long userId,
            @PathVariable @Min(0) long eventId,
            @RequestParam(defaultValue = "DESC") SortComment sort,
            @RequestParam(defaultValue = "0", required = false) @Min(0) int from,
            @RequestParam(defaultValue = "10", required = false) @Min(1) int size) {
        log.info("GET comments userId={} by eventId={}, from={}, size={}", userId, eventId, from, size);
        return CommentMapper.toCommentResponseDtoCollection(
                commentService.getByUserIdEventId(userId, eventId, sort, from, size));
    }
}
