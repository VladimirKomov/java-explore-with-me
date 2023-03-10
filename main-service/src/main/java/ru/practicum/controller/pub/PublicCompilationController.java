package ru.practicum.controller.pub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.compliiation.CompilationDto;
import ru.practicum.mapper.CompilationMapper;
import ru.practicum.service.compilation.CompilationService;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collection;

@Validated
@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
@Slf4j
public class PublicCompilationController {

    private final CompilationService compilationService;

    /**
     * Получение подборок событий
     */
    @GetMapping
    public Collection<CompilationDto> getAll(
            @RequestParam(required = false) boolean pinned,
            @PositiveOrZero @RequestParam(defaultValue = "0", required = false) @Min(0) int from,
            @Positive @RequestParam(defaultValue = "10", required = false) @Min(1) int size) {
        log.info("GET compilations pinned={}, from={}, size={}", pinned, from, size);
        return CompilationMapper.toCompilationDtoCollection(
                compilationService.getAll(pinned, from, size));
    }

    /**
     * Получение подборки события по его id
     */
    @GetMapping("{compId}")
    public CompilationDto getById(@PathVariable @Min(0) long compId) {
        log.info("GET compilation compId={}", compId);
        return CompilationMapper.toCompilationDto(
                compilationService.getById(compId));
    }
}
