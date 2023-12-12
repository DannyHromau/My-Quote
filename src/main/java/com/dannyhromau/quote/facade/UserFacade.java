package com.dannyhromau.quote.facade;

import com.dannyhromau.quote.api.dto.UserDto;
import com.dannyhromau.quote.core.base.BaseFacade;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface UserFacade extends BaseFacade<UserDto> {
    @Override
    List<UserDto> getDtos(Pageable pageable);

    @Override
    UserDto getDtoByID(UUID id);

    @Override
    UserDto addDto(UserDto dto);

    @Override
    UUID deleteDtoById(UUID id);

    @Override
    UserDto updateDto(UserDto dto);
}