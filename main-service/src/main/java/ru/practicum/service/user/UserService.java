package ru.practicum.service.user;

import ru.practicum.entity.User;

import java.util.Collection;
import java.util.List;

public interface UserService {

    User create(User user);

    Collection<User> findUsers(List<Long> ids, Integer from, Integer size);

    void deleteById(Long userId);
}
