package ru.practicum.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.category.CategoryResponseDto;
import ru.practicum.dto.category.NewCategoryDto;
import ru.practicum.dto.compliiation.CompilationDto;
import ru.practicum.dto.compliiation.NewCompilationDto;
import ru.practicum.mapper.CategoryMapper;
import ru.practicum.mapper.CompilationMapper;
import ru.practicum.service.compilation.CompilationService;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/admin/compilations")
@RequiredArgsConstructor
@Slf4j
public class AdminCompilationController {

    private final CompilationService compilationService;

    /**
     * Добавление новой подборки
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto createCompilation(@Valid @RequestBody NewCompilationDto dto) {
        log.info("Create {}", dto.toString());
        return CompilationMapper.toCompilationDto(
                compilationService.create(CompilationMapper.toCompilation(dto)));
    }

    /**
     * Обновление подборки
     */
    @PatchMapping("{compId}")
    @ResponseStatus(HttpStatus.OK)
    public CompilationDto update(@Valid @RequestBody NewCompilationDto dto,
                                      @PathVariable @Min(1) long compId) {
        log.info("Update by id={}, for {}", compId, dto.toString());
        return CompilationMapper.toCompilationDto(
                compilationService.update(compId, CompilationMapper.toCompilation(dto)));
    }
}
