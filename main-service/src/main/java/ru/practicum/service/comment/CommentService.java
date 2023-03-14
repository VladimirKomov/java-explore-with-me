package ru.practicum.service.comment;

import ru.practicum.entity.Comment;
import ru.practicum.entity.SortComment;

import javax.validation.constraints.Min;
import java.util.Collection;

public interface CommentService {

    Comment create(long userId, long eventId, Comment comment);

    Comment update(long userId, long commentId, Comment Comment);

    Comment getById(long id);

    Collection<Comment> getByEventId(long eventId, SortComment sort, @Min(0) int from, @Min(1) int size);

    Collection<Comment> getByUserId(long userId, SortComment sort, int from, int size);

    Collection<Comment> getByUserIdEventId(long userId, long eventId, SortComment sort, int from, int size);

    void deleteById(long userId, long commentId);

    void deleteByIdForAdmin(long commentId);

    Comment updateForAdmin(long commentId, Comment comment);
}
