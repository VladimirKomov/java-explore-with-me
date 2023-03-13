package ru.practicum.dto.compliiation;

import lombok.Data;
import ru.practicum.validation.group.Create;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class NewCompilationDto {
    @NotNull(groups = {Create.class})
    private Set<Long> events;
    @NotNull(groups = {Create.class})
    private Boolean pinned;
    @NotNull(groups = {Create.class})
    private String title;
}
