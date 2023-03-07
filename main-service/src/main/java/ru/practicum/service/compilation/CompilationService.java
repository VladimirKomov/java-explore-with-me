package ru.practicum.service.compilation;

import ru.practicum.entity.Compilation;

import java.util.Collection;

public interface CompilationService {

    Collection<Compilation> getAll(boolean pinned, int from, int size);

    Compilation create(Compilation toCompilation);

    Compilation update(long compId, Compilation compilation);

    void delete(long compId);

    Compilation getById(long compId);
}
