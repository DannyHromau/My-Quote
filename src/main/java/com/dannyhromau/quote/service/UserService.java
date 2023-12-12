package com.dannyhromau.quote.service;

import com.dannyhromau.quote.core.base.BaseService;
import com.dannyhromau.quote.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService extends BaseService<User> {
    @Override
    List<User> getAll(Pageable pageable);

    @Override
    User getById(UUID id);

    @Override
    User add(User entity);

    @Override
    UUID deleteById(UUID id);

    @Override
    User update(User entity);
    User validateId(UUID id);

    User getEntityByEmail(String email);
    User getEntityByLogin(String login);
}
