package com.dannyhromau.quote.core.security.provider.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private int accessExpiration;
    private int refreshExpiration;
    private String algorithm;
}
