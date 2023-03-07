package ru.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.entity.Request;

import java.util.Collection;

public interface RequestRepository extends JpaRepository<Request, Long> {

    Collection<Request> findAllByRequesterId(long id);

    Collection<Request> findAllByRequesterIdAndEventId(long userId, long eventId);

}
