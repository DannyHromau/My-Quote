package com.dannyhromau.quote.facade.impl;

import com.dannyhromau.quote.api.dto.UserDto;
import com.dannyhromau.quote.facade.UserFacade;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserFacadeImpl implements UserFacade {
    @Override
    public List<UserDto> getDtos(Pageable pageable) {
        return null;
    }

    @Override
    public UserDto getDtoByID(UUID id) {
        return null;
    }

    @Override
    public UserDto addDto(UserDto dto) {
        return null;
    }

    @Override
    public UUID deleteDtoById(UUID id) {
        return null;
    }

    @Override
    public UserDto updateDto(UserDto dto) {
        return null;
    }
}
