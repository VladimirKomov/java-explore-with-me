package ru.practicum.dto.compliiation;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class NewCompilationDto {
    private Set<Long> events;
    private Long id;
    private Boolean pinned;
    @NotNull
    private String title;
}
