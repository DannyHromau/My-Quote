package com.dannyhromau.quote.mapper;

import com.dannyhromau.quote.api.dto.TokenDto;
import com.dannyhromau.quote.core.security.provider.jwt.JwToken;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenMapper {
    JwToken mapToToken(TokenDto tokenDto);

    TokenDto mapToTokenDto(JwToken token);
}
