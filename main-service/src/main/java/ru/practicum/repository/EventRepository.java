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


//    @Query("FROM Event e WHERE " +
//            "(e.eventDate between :rangeStart and :rangeEnd) and " +
//            "(CASE when (:initiators is null) then (e.initiator.id > 1) else (e.initiator.id in :initiators) end) and " +
//            "(CASE when (:states is null) then (e.state is not null ) else (e.state in :states) end) and " +
//            "(CASE when (:categories is null) then (e.category.id > 1) else (e.category in :categories) end) ")
//    Collection<Event> findByParameters(List<Long> initiators,
//                                       List<State> states,
//                                       List<Long> categories,
//                                       Timestamp rangeStart,
//                                       Timestamp rangeEnd,
//                                       PageRequest pageRequest);

//    @Query("FROM Event e " +
//            "WHERE " +
//            "(e.state in :states or :states is not null ) and " +
//            "( e.category.id in :categories or :categories is not null) and " +
//            "(e.initiator.id in :initiators or :initiators is not null) and" +
//            "(e.eventDate between :rangeStart and :rangeEnd) ")
//    Collection<Event> findByParameters(List<Long> initiators,
//                                       List<State> states,
//                                       List<Long> categories,
//                                       Timestamp rangeStart,
//                                       Timestamp rangeEnd,
//                                       PageRequest pageRequest);
//@Query(value = "SELECT * FROM events e " +
//        "WHERE " +
//        "(:isStates = false or e.state in :states) and " +
//        "(:isCategory = false or e.category_id in :categories) and " +
//        "(:isUsers = false or e.initiator_id in :initiators) and" +
//        "(e.event_date between :rangeStart and :rangeEnd) ",
//        nativeQuery = true)
//    Collection<Event> test(boolean isUsers, boolean isStates, boolean isCategory,
//                                       List<Long> initiators,
//                                       List<State> states,
//                                       List<Long> categories,
//                                       Timestamp rangeStart,
//                                       Timestamp rangeEnd,
//                                       PageRequest pageRequest);

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
    Collection<Event> findByParametersForPublic(
            String text,
            List<Long> categories,
            Boolean paid,
            Timestamp rangeStart,
            Timestamp rangeEnd,
            Boolean onlyAvailable,
            PageRequest pageRequest);


//    @Query("FROM Event e WHERE " +
//            "(e.state = 'PUBLISHED') and " +
//            "(e.eventDate between :rangeStart and :rangeEnd) and " +
//            "(e.category.id in :categories or :categories is null) and " +
//            "(e.paid = :paid or :paid is null) and " +
//            "(e.participantLimit > e.confirmedRequests or :onlyAvailable is null ) and " +
//            "((lower(e.annotation) like %:text% or lower(e.description) like %:text%) or :text is null)")
//    Collection<Event> findByParametersForPublic(String text,
//                                                List<Long> categories,
//                                                Boolean paid,
//                                                Timestamp rangeStart,
//                                                Timestamp rangeEnd,
//                                                Boolean onlyAvailable,
//                                                PageRequest pageRequest);

}
