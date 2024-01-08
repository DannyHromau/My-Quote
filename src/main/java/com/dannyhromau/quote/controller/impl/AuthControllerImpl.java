package com.dannyhromau.quote.controller.impl;

import com.dannyhromau.quote.api.dto.AuthDto;
import com.dannyhromau.quote.api.dto.TokenDto;
import com.dannyhromau.quote.controller.AuthController;
import com.dannyhromau.quote.facade.AuthFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthFacade authFacade;
    @Override
    public ResponseEntity<Boolean> register(@RequestBody @NonNull AuthDto authDto) {
        log.info("call register");
        return ResponseEntity.ok(authFacade.register(authDto));
    }

    @Override
    public ResponseEntity<TokenDto> login(@RequestBody @NonNull AuthDto authDto) {
        log.info("call login");
        return ResponseEntity.ok(authFacade.authorize(authDto));
    }

    @Override
    public ResponseEntity<TokenDto> refresh(@RequestBody @NonNull TokenDto tokenDto) {
        log.info("call refresh token");
        return ResponseEntity.ok(authFacade.refresh(tokenDto));
    }
}
