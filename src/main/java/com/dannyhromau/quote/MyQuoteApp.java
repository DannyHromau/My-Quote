package com.dannyhromau.quote;

import com.dannyhromau.quote.core.config.LimitConfig;
import com.dannyhromau.quote.core.security.config.AuthValidationConfig;
import com.dannyhromau.quote.core.security.config.SecurityUrlConfig;
import com.dannyhromau.quote.core.security.provider.jwt.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({SecurityUrlConfig.class, AuthValidationConfig.class, JwtConfig.class, LimitConfig.class})
public class MyQuoteApp {
    public static void main(String[] args) {
        SpringApplication.run(MyQuoteApp.class, args);
    }
}
