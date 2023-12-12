package com.dannyhromau.quote.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "limit")
public class LimitConfig {
    private int lastVoteLimit;
    private int randomQuoteLimit;
}
