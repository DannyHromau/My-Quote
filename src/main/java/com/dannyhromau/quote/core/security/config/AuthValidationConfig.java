package com.dannyhromau.quote.core.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "authentication")
public class AuthValidationConfig {
    private String passwordPattern;
    private String emailPattern;
}
