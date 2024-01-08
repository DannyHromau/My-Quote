package com.dannyhromau.quote.service.impl;


import com.dannyhromau.quote.core.config.LimitConfig;
import com.dannyhromau.quote.core.util.ErrorMessages;
import com.dannyhromau.quote.core.util.TokenUtil;
import com.dannyhromau.quote.exception.EntityNotfoundException;
import com.dannyhromau.quote.exception.ForbiddenException;
import com.dannyhromau.quote.exception.InvalidDataException;
import com.dannyhromau.quote.model.Quote;
import com.dannyhromau.quote.model.Vote;
import com.dannyhromau.quote.model.VoteValue;
import com.dannyhromau.quote.repository.QuoteRepository;
import com.dannyhromau.quote.service.QuoteService;
import com.dannyhromau.quote.service.UserService;
import com.dannyhromau.quote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;
    private final UserService userService;
    private final VoteService voteService;
    private final TokenUtil tokenUtil;
    private final LimitConfig limitConfig;
    private static final String ENTITY_NOT_FOUND_MESSAGE = ErrorMessages.ENTITY_NOT_FOUND_MESSAGE.label;
    private static final String NULLABLE_ID_MESSAGE = ErrorMessages.NULLABLE_ID_MESSAGE.label;
    private static final String INCORRECT_DATA_MESSAGE = ErrorMessages.INCORRECT_DATA_MESSAGE.label;
    private static final String FORBIDDEN_STATUS_MESSAGE = ErrorMessages.FORBIDDEN_STATUS_MESSAGE.label;


    public Quote getRandomQuote(int limit) {
        limit = limit < 0 ? limitConfig.getRandomQuoteLimit() : limit;
        return quoteRepository.findRandomQuote(limit);
    }

    public List<Quote> getTopQuotes(Pageable pageable) {
        Map<Quote, Integer> topMap = new HashMap<>();
        List<Quote> topQuotes = quoteRepository.findTopQuotes(pageable.getPageSize());
        for (Quote quote : topQuotes) {
            int topCount = voteService.getTopVotesValueByQuoteId(quote.getId());
            topMap.put(quote, topCount);
        }
        return topMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<Quote> getFlopQuotes(Pageable pageable) {
        Map<Quote, Integer> flopMap = new HashMap<>();
        List<Quote> flopQuotes = quoteRepository.findFlopQuotes(pageable.getPageSize());
        for (Quote quote : flopQuotes) {
            int flopCount = voteService.getFlopVotesValueByQuoteId(quote.getId());
            flopMap.put(quote, flopCount);
        }
        return flopMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public List<Quote> getAll(Pageable pageable) {
        return quoteRepository.findAll(pageable).toList();
    }

    @Override
    public Quote getById(UUID id) {
        return validateId(id);
    }

    @Override
    public Quote add(Quote quote) {
        if (quote.getId() != null || quote.getContent().isBlank()) {
            throw new InvalidDataException(INCORRECT_DATA_MESSAGE);
        }
        quote.setUser(userService.getById(quote.getUserId()));
        quote.setCreatedOn(ZonedDateTime.now());
        quote.setUpdatedOn(ZonedDateTime.now());
        return quoteRepository.save(quote);
    }

    @Override
    public UUID deleteById(UUID id) {
        id = checkAuthorities(id);
        quoteRepository.deleteById(id);
        return id;
    }

    @Override
    public Quote update(Quote quote) {
        quote = validateId(quote.getId());
        checkAuthorities(quote.getId());
        quote.setUpdatedOn(ZonedDateTime.now());
        return quoteRepository.save(quote);
    }

    @Override
    public Quote validateId(UUID id) {
        if (id == null) {
            throw new InvalidDataException(NULLABLE_ID_MESSAGE);
        }
        Optional<Quote> quoteOpt = quoteRepository.findById(id);
        if (quoteOpt.isEmpty()) {
            throw new EntityNotfoundException(String.format(ENTITY_NOT_FOUND_MESSAGE, id));
        } else {
            return quoteOpt.get();
        }
    }

    public UUID checkAuthorities(UUID id) {
        if (tokenUtil.getUserDetails().getId().equals(id)) {
            return id;
        } else {
            throw new ForbiddenException(FORBIDDEN_STATUS_MESSAGE);
        }
    }
}
