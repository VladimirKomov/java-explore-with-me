package ru.practicum.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.entity.Comment;

import java.util.Collection;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Collection<Comment> findAllByEventIdOrderByCreatedOnAsc(long eventId, PageRequest pageRequest);

    Collection<Comment> findAllByEventIdOrderByCreatedOnDesc(long eventId, PageRequest pageRequest);

    Collection<Comment> findAllByAuthorIdOrderByCreatedOnAsc(long userId, PageRequest pageRequest);

    Collection<Comment> findAllByAuthorIdOrderByCreatedOnDesc(long userId, PageRequest pageRequest);

    Collection<Comment> findAllByAuthorIdAndAndEventIdOrderByCreatedOnAsc(long userId, long eventId,
                                                                          PageRequest pageRequest);

    Collection<Comment> findAllByAuthorIdAndEventIdOrderByCreatedOnDesc(long userId, long eventId,
                                                                        PageRequest pageRequest);
}
