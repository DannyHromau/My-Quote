package com.dannyhromau.quote.core.security.config;

import com.nimbusds.jose.JWSAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private static final String SPEC_KEY = "SecretSpecialKeyOauth2.0Jwt256Bites";
    private final SecurityUrlConfig securityUrlConfig;
    @Value("${logoutUrl}")
    private String logoutUrl;
    @Value("${logoutSuccessUrl}")
    private String logoutSuccessUrl;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String[] patternsArr = securityUrlConfig.getUrls().toArray(new String[0]);
        http
                .authorizeHttpRequests()
                .antMatchers(patternsArr)
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .logout(
                        logout -> {
                            logout
                                    .logoutUrl(logoutUrl)
                                    .logoutSuccessUrl(logoutSuccessUrl)
                                    .logoutSuccessHandler(getLogoutSuccessHandler())
                                    .invalidateHttpSession(true)
                                    .clearAuthentication(true);
                        }
                )
                .oauth2ResourceServer().jwt().decoder(jwtDecoder());
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withSecretKey(
                new SecretKeySpec(SPEC_KEY.getBytes(StandardCharsets.UTF_8),
                        JWSAlgorithm.RS512.getName())).build();
    }
    @Bean
    protected LogoutSuccessHandler getLogoutSuccessHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}