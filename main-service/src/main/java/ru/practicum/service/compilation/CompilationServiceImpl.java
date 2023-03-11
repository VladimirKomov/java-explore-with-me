package ru.practicum.service.compilation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.entity.Compilation;
import ru.practicum.exception.NotFoundException;
import ru.practicum.mapper.CompilationMapper;
import ru.practicum.repository.CompilationRepository;
import ru.practicum.service.event.EventService;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {

    private final CompilationRepository compilationRepository;
    private final EventService eventService;

    @Override
    public Collection<Compilation> getAll(boolean pinned, int from, int size) {
        return compilationRepository.findAllByPinned(pinned, PageRequest.of(from, size));
    }

    @Override
    public Compilation create(Compilation compilation) {
        compilation.setEvents(eventService.getAll(
                compilation.getEvents().stream()
                        .map(event -> event.getId())
                        .collect(Collectors.toList())
        ).stream().collect(Collectors.toSet()));

        return compilationRepository.save(compilation);
    }


    @Override
    public Compilation update(long compId, Compilation compilation) {
        Compilation recipient = getById(compId);
        return compilationRepository.save(CompilationMapper.update(recipient, compilation));
    }

    @Override
    public void delete(long compId) {
        compilationRepository.deleteById(compId);
    }

    @Override
    public Compilation getById(long compId) {
        return compilationRepository.findById(compId).orElseThrow(
                () -> new NotFoundException("Collection with id=" + compId));
    }
}
