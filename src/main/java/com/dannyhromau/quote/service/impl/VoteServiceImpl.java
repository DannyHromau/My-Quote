package com.dannyhromau.quote.service.impl;

import com.dannyhromau.quote.core.config.LimitConfig;
import com.dannyhromau.quote.core.util.ErrorMessages;
import com.dannyhromau.quote.exception.EntityNotfoundException;
import com.dannyhromau.quote.exception.InvalidDataException;
import com.dannyhromau.quote.model.Vote;
import com.dannyhromau.quote.repository.VoteRepository;
import com.dannyhromau.quote.service.VoteService;
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
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final LimitConfig limitConfig;
    private static final String NULLABLE_ID_MESSAGE = ErrorMessages.NULLABLE_ID_MESSAGE.label;
    private static final String ENTITY_NOT_FOUND_MESSAGE = ErrorMessages.ENTITY_NOT_FOUND_MESSAGE.label;
    private static final String DUPLICATE_USER_MESSAGE = ErrorMessages.INCORRECT_DATA_MESSAGE.label;



    public List<Vote> getLastVotes(int limit){
        limit = limit < 0 ? limitConfig.getLastVoteLimit() : limit;
        return voteRepository.findLastVotes(limit);
    }

    @Override
    public List<Vote> getAll(Pageable pageable) {
        return voteRepository.findAll(pageable).toList();
    }

    @Override
    public Vote getById(UUID id) {
       return validateId(id);
    }

    @Override
    public Vote add(Vote vote) {
        UUID quoteId = vote.getQuoteId();
        UUID userId = vote.getUserId();
        if (voteRepository.findByUserIdAndQuoteId(quoteId, userId) != null) {
            throw new InvalidDataException(DUPLICATE_USER_MESSAGE);
        } else {
            return voteRepository.save(vote);
        }
    }

    @Override
    public UUID deleteById(UUID id) {
        voteRepository.deleteById(validateId(id).getId());
        return id;
    }

    @Override
    public Vote update(Vote vote) {
        vote = validateId(vote.getId());
        return voteRepository.save(vote);
    }

    @Override
    public Vote validateId(UUID id) {
        if (id == null) {
            throw new InvalidDataException(NULLABLE_ID_MESSAGE);
        }
        Optional<Vote> userOpt = voteRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new EntityNotfoundException(String.format(ENTITY_NOT_FOUND_MESSAGE, id));
        } else {
            return userOpt.get();
        }
    }
}
