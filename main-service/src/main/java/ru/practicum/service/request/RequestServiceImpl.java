package ru.practicum.service.request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.entity.*;
import ru.practicum.exception.NotFoundException;
import ru.practicum.repository.RequestRepository;
import ru.practicum.service.event.EventService;
import ru.practicum.service.user.UserService;
import ru.practicum.util.EventRequestStatusUpdateRequest;
import ru.practicum.util.EventRequestStatusUpdateResult;

import javax.validation.ValidationException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final UserService userService;
    private final EventService eventService;

    @Override
    public Collection<Request> getAll(long userId) {
        userService.getById(userId);
        return requestRepository.findAllByRequesterId(userId);
    }

    @Override
    public Request create(long userId, long eventId) {
        User user = userService.getById(userId);
        Event event = eventService.getById(eventId);
        if (event.getInitiator().getId() == userId
                || !event.getState().equals(State.PUBLISHED)
                || event.getConfirmedRequests() >= event.getParticipantLimit()) {
            throw new ValidationException("Invalid request");
        }
        Request newRequest = Request.builder()
                .created(Timestamp.valueOf(LocalDateTime.now()))
                .requester(user)
                .event(event)
                .status(Status.PENDING)
                .build();
        if (!event.getRequestModeration()) {
            newRequest.setStatus(Status.CONFIRMED);
        }

        return requestRepository.save(newRequest);
    }

    @Override
    public Collection<Request> getEventRequests(long userId, long eventId) {
        userService.getById(userId);
        return requestRepository.findAllByRequesterIdAndEventId(userId, eventId);
    }

    //+++ тут бы отладчиком полазить
    @Override
    public EventRequestStatusUpdateResult updateStatus(long userId, long eventId, EventRequestStatusUpdateRequest requestStatusUpdate) {
       // userService.getById(userId);
        //Collection<Request> requests = getEventRequests(userId, eventId);

//        for (Request request:requests) {
//            Event event = request.getEvent();
//            if (event.getParticipantLimit() == 0 || event.getParticipantLimit() > event.getConfirmedRequests()) {
//                request.setStatus(Status.CONFIRMED);
//                //eventService.increaseEventConfirmedRequests(event);
//                requestRepository.save(request);
//                if (event.getParticipantLimit() == event.getConfirmedRequests()) {
//                    //rejectAllEventRequestWithStatusPending(eventId);
//                }
//            }
//        }
        EventRequestStatusUpdateResult requestStatusUpdateResult = new EventRequestStatusUpdateResult();
        return requestStatusUpdateResult;
    }

    @Override
    public Request cancelRequest(long userId, long requestId) {
        userService.getById(userId);
        Request request = getRequestById(requestId);
        request.setStatus(Status.CANCELED);
        return requestRepository.save(request);
    }

    private Request getRequestById(long requestId) {
        return requestRepository.findById(requestId).orElseThrow(
                () -> new NotFoundException("Request with id=" + requestId));
    }


}
