package ru.practicum.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.entity.Comment;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Collection<Comment> findAllByEventIdOrderByCreatedOnAsc(long eventId, PageRequest pageRequest);

    Collection<Comment> findAllByEventIdOrderByCreatedOnDesc(long eventId, PageRequest pageRequest);

    Collection<Comment> findAllByAuthorIdOrderByCreatedOnAsc(long userId, PageRequest pageRequest);

    Collection<Comment> findAllByAuthorIdOrderByCreatedOnDesc(long userId, PageRequest pageRequest);

    Collection<Comment> findAllByAuthorIdAndEventIdOrderByCreatedOnAsc(long userId, long eventId,
                                                                       PageRequest pageRequest);

    Collection<Comment> findAllByAuthorIdAndEventIdOrderByCreatedOnDesc(long userId, long eventId,
                                                                        PageRequest pageRequest);

    @Query("FROM Comment c WHERE " +
            "(c.createdOn between :rangeStart and :rangeEnd) and " +
            "(:text is null) or ((lower(c.text) like %:text%)) and " +
            "((:commentIds is null) or (c.id in :commentIds)) and " +
            "((:onlyUpdate = false) or (c.updatedOn is not null)) " +
            "ORDER BY c.createdOn ASC ")
    Collection<Comment> findByParametersForPublicSortAsc(String text,
                                                         List<Long> commentIds,
                                                         Timestamp rangeStart,
                                                         Timestamp rangeEnd,
                                                         boolean onlyUpdate,
                                                         PageRequest pageRequest);

    @Query("FROM Comment c WHERE " +
            "(c.createdOn between :rangeStart and :rangeEnd) and " +
            "(:text is null) or ((lower(c.text) like %:text%)) and " +
            "((:commentIds is null) or (c.id in :commentIds)) and " +
            "((:onlyUpdate = false) or (c.updatedOn is not null)) " +
            "ORDER BY c.createdOn DESC ")
    Collection<Comment> findByParametersForPublicSortDes(String text,
                                                         List<Long> commentIds,
                                                         Timestamp rangeStart,
                                                         Timestamp rangeEnd,
                                                         boolean onlyUpdate,
                                                         PageRequest pageRequest);
}
