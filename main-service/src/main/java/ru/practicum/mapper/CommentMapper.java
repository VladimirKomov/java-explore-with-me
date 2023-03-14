package ru.practicum.mapper;

import ru.practicum.dto.comment.CommentResponseDto;
import ru.practicum.dto.comment.NewCommentDto;
import ru.practicum.dto.comment.UpdateCommentDto;
import ru.practicum.entity.Comment;

import java.util.Collection;
import java.util.stream.Collectors;

public class CommentMapper {

    public static Comment toComment(NewCommentDto dto) {
        return Comment.builder()
                .text(dto.getText())
                .build();
    }

    public static CommentResponseDto toCommentResponseDto(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .author(UserMapper.toUserShortDto(comment.getAuthor()))
                .event(EventMapper.toEventShortDto(comment.getEvent()))
                .createdOn(comment.getCreatedOn())
                .updatedOn(comment.getUpdatedOn())
                .text(comment.getText())
                .build();
    }

    public static Comment toComment(UpdateCommentDto dto) {
        return Comment.builder()
                .text(dto.getText())
                .build();
    }

    public static Comment update(Comment donor, Comment recipient) {
        if (donor.getText() != null) recipient.setText(donor.getText());
        return recipient;
    }

    public static Collection<CommentResponseDto> toCommentResponseDtoCollection(Collection<Comment> comments) {
        return comments.stream()
                .map(CommentMapper::toCommentResponseDto)
                .collect(Collectors.toList());
    }

}
