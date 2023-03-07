package ru.practicum.service.user;

import ru.practicum.entity.User;

import java.util.Collection;
import java.util.List;

public interface UserService {

    User create(User user);

    Collection<User> getUsers(List<Long> ids, int from, int size);
    User getById(long userId);

    void deleteById(long userId);
}
