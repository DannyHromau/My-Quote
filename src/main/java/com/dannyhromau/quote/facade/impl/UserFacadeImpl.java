package com.dannyhromau.quote.facade.impl;

import com.dannyhromau.quote.api.dto.UserDto;
import com.dannyhromau.quote.facade.UserFacade;
import com.dannyhromau.quote.mapper.UserMapper;
import com.dannyhromau.quote.model.User;
import com.dannyhromau.quote.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getDtos(Pageable pageable) {
        return userMapper.mapToListUserDto(userService.getAll(pageable));
    }

    @Override
    public UserDto getDtoByID(UUID id) {
        return userMapper.mapToUserDto(userService.getById(id));
    }

    @Override
    public UserDto addDto(UserDto dto) {
        User user = userService.add(userMapper.mapToUser(dto));
        return userMapper.mapToUserDto(user);
    }

    @Override
    public UUID deleteDtoById(UUID id) {
        return userService.deleteById(id);
    }

    @Override
    public UserDto updateDto(UserDto dto) {
        User user = userService.getById(dto.getId());
        userMapper.updateUserFromDto(dto, user);
        user = userService.update(user);
        return userMapper.mapToUserDto(user);
    }
}
