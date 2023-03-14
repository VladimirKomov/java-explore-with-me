package ru.practicum.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.entity.Comment;
import ru.practicum.entity.SortComment;
import ru.practicum.entity.User;
import ru.practicum.exception.NotFoundException;
import ru.practicum.mapper.CommentMapper;
import ru.practicum.repository.CommentRepository;
import ru.practicum.service.event.EventService;
import ru.practicum.service.user.UserService;

import javax.validation.ValidationException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final EventService eventService;

    @Override
    public Comment create(long userId, long eventId, Comment comment) {
        comment.setAuthor(userService.getById(userId));
        comment.setEvent(eventService.getById(eventId));
        comment.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));

        return commentRepository.save(comment);
    }

    @Override
    public Comment update(long userId, long commentId, Comment comment) {
        User user = userService.getById(userId);
        Comment recipient = getById(commentId);
        if (userId != user.getId()) {
            throw new ValidationException("Error: userId/commentId");
        }
        recipient = updateFields(comment, recipient);

        return commentRepository.save(recipient);
    }

    @Override
    public Comment getById(long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new NotFoundException("Comment with id=" + commentId));
    }

    @Override
    public Collection<Comment> getByEventId(long eventId, SortComment sort, int from, int size) {
        eventService.getById(eventId);
        if (sort.equals(SortComment.ASC)) {
            return commentRepository.findAllByEventIdOrderByCreatedOnAsc(eventId, PageRequest.of(from, size));
        }
        return commentRepository.findAllByEventIdOrderByCreatedOnDesc(eventId, PageRequest.of(from, size));
    }

    @Override
    public Collection<Comment> getByUserId(long userId, SortComment sort, int from, int size) {
        userService.getById(userId);
        if (sort.equals(SortComment.ASC)) {
            return commentRepository.findAllByAuthorIdOrderByCreatedOnAsc(userId, PageRequest.of(from, size));
        }
        return commentRepository.findAllByAuthorIdOrderByCreatedOnDesc(userId, PageRequest.of(from, size));
    }

    @Override
    public Collection<Comment> getByUserIdEventId(long userId, long eventId, SortComment sort, int from, int size) {
        userService.getById(userId);
        eventService.getById(eventId);

        if (sort.equals(SortComment.ASC)) {
            return commentRepository.
                    findAllByAuthorIdAndAndEventIdOrderByCreatedOnAsc(userId, eventId, PageRequest.of(from, size));
        }
        return commentRepository.
                findAllByAuthorIdAndEventIdOrderByCreatedOnDesc(userId, eventId, PageRequest.of(from, size));
    }

    @Override
    public void deleteById(long userId, long commentId) {
        userService.getById(userId);
        commentRepository.delete(getById(commentId));
    }

    @Override
    public void deleteByIdForAdmin(long commentId) {
        commentRepository.delete(getById(commentId));
    }

    @Override
    public Comment updateForAdmin(long commentId, Comment comment) {
        Comment recipient = getById(commentId);
        recipient = updateFields(comment, recipient);

        return commentRepository.save(recipient);
    }

    private Comment updateFields(Comment comment, Comment recipient) {
        recipient = CommentMapper.update(comment, recipient);
        recipient.setUpdatedOn(Timestamp.valueOf(LocalDateTime.now()));
        return recipient;
    }

}
