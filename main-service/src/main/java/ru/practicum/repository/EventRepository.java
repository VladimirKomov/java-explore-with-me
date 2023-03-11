package ru.practicum.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.entity.Event;
import ru.practicum.entity.State;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Collection<Event> findAllByInitiatorId(long initiator, PageRequest pageRequest);

    Optional<Event> findByIdAndInitiatorId(long eventId, long userId);

    @Query("FROM Event e where " +
            "(e.eventDate between :rangeStart and :rangeEnd) and " +
            "((:initiators is null) or e.initiator.id in :initiators) and " +
            "((:categories is null) or e.category.id in :categories) and" +
            "((:states is null) or e.state in :states)")
    Collection<Event> findByParameters(List<Long> initiators,
                                       List<State> states,
                                       List<Long> categories,
                                       Timestamp rangeStart,
                                       Timestamp rangeEnd,
                                       PageRequest pageRequest);

    @Query("FROM Event e WHERE " +
            "(e.state = 'PUBLISHED') and " +
            "(e.eventDate between :rangeStart and :rangeEnd) and " +
            "(:text is null) or ((lower(e.annotation) like %:text% or lower(e.description) like %:text%)) and " +
            "((:categories is null) or e.category.id in :categories) and " +
            "((:paid is null) or e.paid = :paid) and " +
            "((:onlyAvailable is null) or e.participantLimit > e.confirmedRequests)")
    Collection<Event> findByParametersForPublic(String text,
                                                List<Long> categories,
                                                Boolean paid,
                                                Timestamp rangeStart,
                                                Timestamp rangeEnd,
                                                Boolean onlyAvailable,
                                                PageRequest pageRequest);

}
