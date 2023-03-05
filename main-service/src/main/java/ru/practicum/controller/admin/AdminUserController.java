package ru.practicum.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.user.NewUserDto;
import ru.practicum.dto.user.UserResponseDto;
import ru.practicum.mapper.UserMapper;
import ru.practicum.service.user.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.List;

@Validated
@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Slf4j
public class AdminUserController {

    private final UserService userService;

    /**
     * Добавление нового пользователя
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto create(@Valid @RequestBody NewUserDto newUserDto) {
        log.info("Create {}", newUserDto.toString());
        return UserMapper.toUserResponseDto(
                userService.create(UserMapper.toUser(newUserDto)));
    }

    /**
     * Возвращает информацию обо всех пользователях (учитываются параметры ограничения выборки),
     * либо о конкретных (учитываются указанные идентификаторы)
     */
    @GetMapping
    public Collection<UserResponseDto> findUsersByParameters(
            @RequestParam(required = false) List<Long> ids,
            @RequestParam(defaultValue = "0") @Min(0) Integer from,
            @RequestParam(defaultValue = "10") @Min(1) Integer size) {
        log.info("GET users ids={}, from={}, size={}", ids, from, size);
        return UserMapper.toUserResponseDtoCollection(
                userService.findUsers(ids, from, size));
    }

    /**
     * Удаление пользователя
     */
    @DeleteMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable @Min(1) Long userId) {
        log.info("Delete by id={}", userId);
        userService.deleteById(userId);
    }

}
