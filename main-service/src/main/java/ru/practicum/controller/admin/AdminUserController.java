package ru.practicum.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.user.UserRequestDto;
import ru.practicum.dto.user.UserResponseDto;
import ru.practicum.mapper.UserMapper;
import ru.practicum.service.user.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Validated
@Slf4j
public class AdminUserController {

    private final UserService userService;

    /**
     * Добавление нового пользователя
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto create(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("Create {}", userRequestDto.toString());
        return UserMapper.toUserResponseDto(
                userService.createUser(UserMapper.toUser(userRequestDto)));
    }

    /**
     * Возвращает информацию обо всех пользователях (учитываются параметры ограничения выборки),
     * либо о конкретных (учитываются указанные идентификаторы)
     */
    @GetMapping
    public Collection<UserResponseDto> findUsersByParameters(
            @RequestParam(required = false) List<Long> ids,
            @Min(0) @RequestParam(defaultValue = "0") Integer from,
            @Min(1) @RequestParam(defaultValue = "10") Integer size) {
        log.info("GET users ids={}, from={}, size={}", ids, from, size);
        return UserMapper.toUserResponseDtoCollection(
                userService.findUsers(ids, from, size));
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@Min(1) @PathVariable Long userId) {
        log.info("Delete by id={}", userId);
        userService.deleteUserById(userId);
    }

}
