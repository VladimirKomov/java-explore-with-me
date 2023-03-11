package ru.practicum.service.compilation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.entity.Compilation;
import ru.practicum.entity.Event;
import ru.practicum.exception.NotFoundException;
import ru.practicum.mapper.CompilationMapper;
import ru.practicum.repository.CompilationRepository;
import ru.practicum.service.event.EventService;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;


@Service
@Transactional
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
        compilation.setEvents(new HashSet<>(eventService.getAll(
                compilation.getEvents().stream()
                        .map(Event::getId)
                        .collect(Collectors.toList())
        )));

        return compilationRepository.save(compilation);
    }


    @Override
    public Compilation update(long compId, Compilation compilation) {
        Compilation recipient = getById(compId);
        return compilationRepository.save(CompilationMapper.update(recipient, compilation));
    }

    @Override
    public void delete(long compId) {
        getById(compId);
        compilationRepository.deleteById(compId);
    }

    @Override
    public Compilation getById(long compId) {
        return compilationRepository.findById(compId).orElseThrow(
                () -> new NotFoundException("Collection with id=" + compId));
    }
}
