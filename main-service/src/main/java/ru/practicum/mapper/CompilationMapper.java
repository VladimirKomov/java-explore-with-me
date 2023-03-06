package ru.practicum.mapper;

import ru.practicum.dto.compliiation.CompilationDto;
import ru.practicum.dto.compliiation.NewCompilationDto;
import ru.practicum.entity.Compilation;
import ru.practicum.entity.Event;

import java.util.Collection;
import java.util.stream.Collectors;

public class CompilationMapper {

    public static Compilation toCompilation(NewCompilationDto dto) {
        return Compilation.builder()
                .events(dto.getEvents().stream().map(CompilationMapper::makeEvent).collect(Collectors.toSet()))
                .build();
    }

    public static Collection<CompilationDto> toCompilationDtoCollection(Collection<Compilation> compilations) {
        return compilations.stream()
                .map(CompilationMapper::toCompilationDto)
                .collect(Collectors.toList());
    }

    public static CompilationDto toCompilationDto(Compilation compilation) {
        return CompilationDto.builder()
                .events(compilation.getEvents().stream().map(EventMapper::toEventShortDto).collect(Collectors.toSet()))
                .id(compilation.getId())
                .pinned(compilation.getPinned())
                .title(compilation.getTitle())
                .build();
    }

    public static Compilation update(Compilation recipient, Compilation donor) {
        if (donor.getEvents() != null) recipient.setEvents(donor.getEvents());
        if (donor.getPinned() != null) recipient.setPinned(donor.getPinned());
        if (donor.getTitle() != null) recipient.setTitle(donor.getTitle());
        return recipient;
    }

    private static Event makeEvent(Long id) {
        return Event.builder()
                .id(id)
                .build();
    }

}
