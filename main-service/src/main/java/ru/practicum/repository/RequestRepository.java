package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.entity.Request;
import ru.practicum.util.Status;

import java.util.Collection;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {

    Collection<Request> findAllByRequesterId(long id);

    Collection<Request> findAllByEventId(long eventId);

    @Query("SELECT  count(r.id) " +
            "from Request as r " +
            "WHERE r.event.id = :eventId " +
            "and r.status = :status")
    Optional<Integer> findCountRequestByEventIdAndStatus(long eventId, Status status);

}
