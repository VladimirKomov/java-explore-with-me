package ru.practicum.service.user;

import ru.practicum.entity.User;

import java.util.Collection;
import java.util.List;

public interface UserService {

    User createUser(User user);
    Collection<User> findUsers(List<Long> ids, Integer from, Integer size);
    void deleteUserById(Long userId);
}
