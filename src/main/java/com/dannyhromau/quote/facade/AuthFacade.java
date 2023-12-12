package com.dannyhromau.quote.facade;

import com.dannyhromau.quote.api.dto.AuthDto;
import com.dannyhromau.quote.api.dto.TokenDto;
import org.springframework.stereotype.Component;

@Component
public interface AuthFacade {
    TokenDto authorize(AuthDto authDto);

    boolean register(AuthDto registerDto);

    TokenDto refresh(TokenDto refreshToken);
}
