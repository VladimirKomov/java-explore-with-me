package ru.practicum.controller.priv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.comment.CommentResponseDto;
import ru.practicum.dto.comment.NewCommentDto;
import ru.practicum.dto.comment.UpdateCommentDto;
import ru.practicum.mapper.CommentMapper;
import ru.practicum.service.comment.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/users/{userId}/comments")
@Validated
@RequiredArgsConstructor
@Slf4j
public class PrivateCommentController {

    private final CommentService commentService;

    /**
     * Добавление нового комментария
     */
    @PostMapping("{eventId}/events")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto create(@PathVariable @Min(0) long userId,
                                     @PathVariable @Min(0) long eventId,
                                     @RequestBody @Valid NewCommentDto dto) {
        log.info("Create {} by userId={} for eventId={}", dto.toString(), userId, eventId);
        return CommentMapper.toCommentResponseDto(
                commentService.create(userId, eventId, CommentMapper.toComment(dto)));
    }

    /**
     * Изменение комментария добавленного текущим пользователем
     */
    @PatchMapping("{commentId}")
    public CommentResponseDto update(@PathVariable @Min(0) long userId,
                                     @PathVariable @Min(0) long commentId,
                                     @RequestBody @Valid UpdateCommentDto dto) {
        log.info("Update {} by userId={} the commentId={}", dto.toString(), userId, commentId);
        return CommentMapper.toCommentResponseDto(
                commentService.update(userId, commentId, CommentMapper.toComment(dto)));
    }

    /**
     * Удаление комментария добавленного текущим пользователем
     */
    @DeleteMapping("{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommentById(@PathVariable @Min(0) long userId,
                                  @PathVariable @Min(0) long commentId) {
        log.info("Delete by userId={} the commentId={}", userId, commentId);
        commentService.deleteById(userId, commentId);
    }

}
