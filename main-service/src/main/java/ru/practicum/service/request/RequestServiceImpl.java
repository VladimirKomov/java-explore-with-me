package ru.practicum.service.request;

import org.springframework.stereotype.Service;
import ru.practicum.entity.Request;

import java.util.Collection;

@Service
public class RequestServiceImpl implements RequestService{
    @Override
    public Collection<Request> getAll(Long userId) {
        return null;
    }

    @Override
    public Request create(Long userId, Long eventId) {
        return null;
    }
}
