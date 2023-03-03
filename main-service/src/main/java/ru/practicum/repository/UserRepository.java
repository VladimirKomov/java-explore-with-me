package ru.practicum.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.entity.User;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("FROM User WHERE id in :ids or :ids is null")
    Collection<User> findByIdIn(Collection<Long> ids, PageRequest pageRequest);

}
