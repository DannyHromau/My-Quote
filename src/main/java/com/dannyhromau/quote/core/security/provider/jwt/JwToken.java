package com.dannyhromau.quote.core.security.provider.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwToken {
    private String accessToken;
    private String refreshToken;
}
