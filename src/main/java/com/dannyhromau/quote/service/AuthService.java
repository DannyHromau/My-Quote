package com.dannyhromau.quote.service;

import com.dannyhromau.quote.core.security.provider.jwt.JwToken;
import com.dannyhromau.quote.model.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    boolean register(User user);

    JwToken authorize(User user);

    JwToken refresh(JwToken token);

    User checkValidData(User user);
}
