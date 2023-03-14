package ru.practicum.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.comment.CommentResponseDto;
import ru.practicum.dto.comment.UpdateCommentDto;
import ru.practicum.mapper.CommentMapper;
import ru.practicum.service.comment.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping(path = "/admin/comments")
@RequiredArgsConstructor
@Slf4j
public class AdminCommentController {

    private final CommentService commentService;

    /**
     * Удаление комментария добавленного любым пользователем
     */
    @DeleteMapping("{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable @Min(0) long commentId) {
        log.info("Delete by id={}", commentId);
        commentService.deleteByIdForAdmin(commentId);
    }

    /**
     * Изменение комментария добавленного любым пользователем
     */
    @PatchMapping("{commentId}")
    public CommentResponseDto update(@PathVariable @Min(0) long commentId,
                                     @RequestBody @Valid UpdateCommentDto dto) {
        log.info("Update {} by commentId={}", dto.toString(), commentId);
        return CommentMapper.toCommentResponseDto(
                commentService.updateForAdmin(commentId, CommentMapper.toComment(dto)));
    }
}
