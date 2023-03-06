package ru.practicum.dto.compliiation;

import lombok.Builder;
import lombok.Data;
import ru.practicum.dto.event.EventShortDto;

import java.util.Set;

@Data
@Builder
public class CompilationDto {

    private Set<EventShortDto> events;
    private Long id;
    private Boolean pinned;
    private String title;

}
