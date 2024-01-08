package com.dannyhromau.quote.service.impl;

import com.dannyhromau.quote.core.security.config.AuthValidationConfig;
import com.dannyhromau.quote.core.security.config.WebSecurityConfig;
import com.dannyhromau.quote.core.security.provider.jwt.JwToken;
import com.dannyhromau.quote.core.security.provider.jwt.JwtProvider;
import com.dannyhromau.quote.core.util.ErrorMessages;
import com.dannyhromau.quote.exception.InvalidDataException;
import com.dannyhromau.quote.exception.UnAuthorizedException;
import com.dannyhromau.quote.model.User;
import com.dannyhromau.quote.service.AuthService;
import com.dannyhromau.quote.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtProvider tokenProvider;
    private final AuthValidationConfig avConfig;
    private final WebSecurityConfig securityConfig;
    private final BCryptPasswordEncoder encoder;
    private static final String WRONG_EMAIL_FORMAT_MESSAGE = ErrorMessages.WRONG_EMAIL_MESSAGE.label;
    private static final String WRONG_PASSWORD_FORMAT_MESSAGE = ErrorMessages.WRONG_PASSWORD_MESSAGE.label;
    private static final String WRONG_AUTHENTICATION_MESSAGE = ErrorMessages.WRONG_AUTHENTICATION_MESSAGE.label;
    private static final String WRONG_REFRESH_TOKEN_MESSAGE = ErrorMessages.WRONG_TOKEN_MESSAGE.label;
    private static final String WRONG_LOGIN_MESSAGE = ErrorMessages.WRONG_LOGIN_MESSAGE.label;
    private String emailRegex = "^(.+)@(\\S+)$";
    private String passwordPatternRegex = "\\S{8,20}";
    private String loginRegex = "^[a-zA-Z0-9._-]{3,15}$";

    @Override
    public boolean register(User user) {
        user = checkValidData(user);
        user.setPassword(encoder.encode(user.getPassword()));
        return userService.add(user) != null;
    }

    @Override
    public JwToken authorize(User user) {
        try {
            User authUser = userService.getEntityByLogin(user.getLogin());
            if (authUser != null && encoder.matches(user.getPassword(), authUser.getPassword())) {
                String accessToken = tokenProvider.createToken(authUser.getId(), authUser.getLogin(), authUser.getEmail());
                String refreshToken = tokenProvider.refreshToken(authUser.getId());
                return new JwToken(accessToken, refreshToken);
            } else {
                throw new UnAuthorizedException(WRONG_AUTHENTICATION_MESSAGE);
            }
        } catch (Exception e) {
            throw new UnAuthorizedException(WRONG_AUTHENTICATION_MESSAGE);
        }
    }

    @Override
    public JwToken refresh(JwToken token) {
        try {
            UUID id = UUID
                    .fromString((String) securityConfig
                            .jwtDecoder()
                            .decode(token.getRefreshToken())
                            .getClaims()
                            .get("id"));
            User user = userService.getById(id);
            return new JwToken(tokenProvider.createToken(user.getId(), user.getLogin(), user.getEmail()),
                    tokenProvider.refreshToken(user.getId()));
        } catch (Exception e) {
            throw new UnAuthorizedException(WRONG_REFRESH_TOKEN_MESSAGE);
        }
    }

    @Override
    public User checkValidData(User user) {
        emailRegex = avConfig.getEmailPattern() == null ? emailRegex : avConfig.getEmailPattern();
        passwordPatternRegex = avConfig.getPasswordPattern() == null ?
                passwordPatternRegex : avConfig.getPasswordPattern();
//        if (!user.getEmail().matches(emailRegex)) {
//            throw new InvalidDataException(WRONG_EMAIL_FORMAT_MESSAGE);
//        }
        if (!user.getPassword().matches(passwordPatternRegex)) {
            throw new InvalidDataException(WRONG_PASSWORD_FORMAT_MESSAGE);
        }
        loginRegex = avConfig.getLoginPattern() == null ? loginRegex : avConfig.getLoginPattern();
        if (!user.getLogin().matches(loginRegex)) {
            throw new InvalidDataException(WRONG_LOGIN_MESSAGE);
        }
        return user;
    }
}
