package ru.practicum.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.entity.User;
import ru.practicum.exception.NotFoundException;
import ru.practicum.repository.UserRepository;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<User> findUsers(List<Long> ids, Integer from, Integer size) {
        return userRepository.findByIdIn(ids, PageRequest.of(from, size));
    }

    @Override
    public User findByid(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id=" + userId));
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
