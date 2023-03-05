package ru.practicum.mapper;

import ru.practicum.dto.event.EventFullDto;
import ru.practicum.dto.event.EventShortDto;
import ru.practicum.dto.event.NewEventDto;
import ru.practicum.dto.event.UpdateEventUserRequest;
import ru.practicum.entity.Event;
import ru.practicum.entity.State;

import java.util.Collection;
import java.util.stream.Collectors;

public class EventMapper {

    public static Event toEvent(NewEventDto dto) {
        return Event.builder()
                .annotation(dto.getAnnotation())
                //+.category()
                //-.createdOn()
                .description(dto.getDescription())
                .eventDate(dto.getEventDate())
                //+.initiator()
                //?.location()
                .paid(dto.isPaid())
                .participantLimit(dto.getParticipantLimit())
                //- .publishedOn()
                .requestModeration(dto.isRequestModeration())
                //?.state()
                .title(dto.getTitle())
                //views()
                .build();
    }

    public static Event toEvent(UpdateEventUserRequest dto) {
        return Event.builder()
                .annotation(dto.getAnnotation())
                //+.category()
                //-.createdOn()
                .description(dto.getDescription())
                .eventDate(dto.getEventDate())
                //+.initiator()
                //?.location()
                .paid(dto.getPaid())
                .participantLimit(dto.getParticipantLimit())
                //- .publishedOn()
                .requestModeration(dto.getRequestModeration())
                .state(findState(dto.getStateAction()))
                .title(dto.getTitle())
                //views()
                .build();
    }

    public static EventFullDto toEventFullDto(Event event) {
        return EventFullDto.builder()
                .id(event.getId())
                .annotation(event.getAnnotation())
                .category(CategoryMapper.toCategoryResponseDto(event.getCategory()))
                .confirmedRequests(event.getConfirmedRequests())
                .createdOn(event.getCreatedOn().toLocalDateTime())
                .description(event.getDescription())
                .eventDate(event.getEventDate())
                .initiator(UserMapper.toUserShortDto(event.getInitiator()))
                .location(LocationMapper.toLocationResponseDto(event.getLocation()))
                .paid(event.getPaid())
                .participantLimit(event.getParticipantLimit())
                .publishedOn(event.getPublishedOn().toLocalDateTime())
                .requestModeration(event.getRequestModeration())
                .state(event.getState().toString())
                .title(event.getTitle())
                .views(event.getViews().intValue())
                .build();
    }

    public static Event updateEvent(Event donor, Event recipient) {
        if (donor.getAnnotation() != null) recipient.setLocation(donor.getLocation());
        if (donor.getCategory() != null) recipient.setCategory(donor.getCategory());
        if (donor.getDescription() != null) recipient.setDescription(donor.getDescription());
        if (donor.getEventDate() != null) recipient.setEventDate(donor.getEventDate());
        if (donor.getLocation() != null) recipient.setLocation(donor.getLocation());
        if (donor.getPaid() != null) recipient.setLocation(donor.getLocation());
        if (donor.getParticipantLimit() != null) recipient.setParticipantLimit(donor.getParticipantLimit());
        if (donor.getRequestModeration() != null) recipient.setRequestModeration(donor.getRequestModeration());
        if (donor.getState() != null) recipient.setState(donor.getState());
        if (donor.getTitle() != null) recipient.setTitle(donor.getTitle());
        return recipient;
    }

    public static Collection<EventShortDto> toEventShortDtoCollection(Collection<Event> events) {
        return events.stream()
                .map(EventMapper::toEventShortDto)
                .collect(Collectors.toList());
    }

    public static EventShortDto toEventShortDto(Event event) {
        return EventShortDto.builder()
                .annotation(event.getAnnotation())
                .category(CategoryMapper.toCategoryResponseDto(event.getCategory()))
                .confirmedRequests(event.getConfirmedRequests())
                .eventDate(event.getEventDate().toLocalDateTime())
                .initiator(UserMapper.toUserShortDto(event.getInitiator()))
                .paid(event.getPaid())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
    }

    private static State findState(String str) {
        if (str == null) return null;
        if (str.equals("CANCEL_REVIEW")) return State.CANCELED;
        return null;
    }
}
