package com.dannyhromau.quote.facade.impl;

import com.dannyhromau.quote.api.dto.AuthDto;
import com.dannyhromau.quote.api.dto.TokenDto;
import com.dannyhromau.quote.core.security.provider.jwt.JwToken;
import com.dannyhromau.quote.facade.AuthFacade;
import com.dannyhromau.quote.mapper.TokenMapper;
import com.dannyhromau.quote.model.User;
import com.dannyhromau.quote.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthFacadeImpl implements AuthFacade {
    private final AuthService authService;
    private final TokenMapper tokenMapper;

    @Override
    public TokenDto authorize(AuthDto authDto) {
        User user = new User(authDto.getLogin(), authDto.getPassword());
        JwToken token = authService.authorize(user);
        return tokenMapper.mapToTokenDto(token);
    }

    @Override
    public boolean register(AuthDto registerDto) {
        User user = new User(registerDto.getLogin(), registerDto.getPassword(), registerDto.getEmail());
        return authService.register(user);
    }

    @Override
    public TokenDto refresh(TokenDto refreshToken) {
        JwToken token = authService.refresh(tokenMapper.mapToToken(refreshToken));
        return tokenMapper.mapToTokenDto(token);
    }
}
