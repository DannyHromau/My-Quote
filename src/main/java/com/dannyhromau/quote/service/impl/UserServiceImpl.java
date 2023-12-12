package com.dannyhromau.quote.service.impl;

import com.dannyhromau.quote.core.util.ErrorMessages;
import com.dannyhromau.quote.exception.EntityNotfoundException;
import com.dannyhromau.quote.exception.InvalidDataException;
import com.dannyhromau.quote.model.User;
import com.dannyhromau.quote.repository.UserRepository;
import com.dannyhromau.quote.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private static final String DUPLICATE_USER_MESSAGE = ErrorMessages.INCORRECT_DATA_MESSAGE.label;
    private static final String NULLABLE_ID_MESSAGE = ErrorMessages.NULLABLE_ID_MESSAGE.label;
    private static final String ENTITY_NOT_FOUND_MESSAGE = ErrorMessages.ENTITY_NOT_FOUND_MESSAGE.label;
    private static final String EXISTING_EMAIL_MESSAGE = ErrorMessages.EXISTING_EMAIL_MESSAGE.label;

    @Override
    public List<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable).toList();
    }

    @Override
    public User getById(UUID id) {
        return validateId(id);
    }

    @Override
    public User add(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new InvalidDataException(DUPLICATE_USER_MESSAGE);
        } else {
            return userRepository.save(user);
        }
    }

    @Override
    public UUID deleteById(UUID id) {
        userRepository.deleteById(validateId(id).getId());
        return id;
    }

    @Override
    public User update(User user) {
        User updatableUser = validateId(user.getId());
        if (!updatableUser.getEmail().equals(user.getEmail())){
            if (userRepository.findByEmail(user.getEmail()) != null){
                throw new InvalidDataException(EXISTING_EMAIL_MESSAGE);
            }
            else {
                userRepository.save(user);
            }
        }
        return userRepository.save(user);
    }

    @Override
    public User validateId(UUID id) {
        if (id == null) {
            throw new InvalidDataException(NULLABLE_ID_MESSAGE);
        }
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new EntityNotfoundException(String.format(ENTITY_NOT_FOUND_MESSAGE, id));
        } else {
            return userOpt.get();
        }
    }

    @Override
    public User getEntityByLogin(String login) {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new IllegalArgumentException(ENTITY_NOT_FOUND_MESSAGE);
        }
        return user;
    }

    @Override
    public User getEntityByEmail(@NonNull String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException(ENTITY_NOT_FOUND_MESSAGE);
        }
        return user;
    }
}
