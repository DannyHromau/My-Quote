package com.dannyhromau.quote.mapper;

import com.dannyhromau.quote.api.dto.UserDto;
import com.dannyhromau.quote.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {VoteMapper.class, QuoteMapper.class})
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User mapToUser(UserDto userDto);

    UserDto mapToUserDto(User user);


    void updateUserFromDto(UserDto userDto, @MappingTarget User user);

    List<UserDto> mapToListUserDto(List<User> users);
}
