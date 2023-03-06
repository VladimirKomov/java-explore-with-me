package ru.practicum.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.entity.Event;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    Collection<Event> findAllByInitiatorId(long initiator, PageRequest pageRequest);

    @Query("FROM Event e WHERE " +
            "(e.eventDate between :rangeStart and :rangeEnd) and " +
            "(e.initiator.id in :initiators or :initiators is null) and " +
            "(e.state in :states or :states is null) and " +
            "(e.category.id in :categories or :categories is null) ")
    Collection<Event> findByParameters(List<Long> initiators,
                                       List<String> states,
                                       List<Long> categories,
                                       Timestamp rangeStart,
                                       Timestamp rangeEnd,
                                       PageRequest pageRequest);
}
