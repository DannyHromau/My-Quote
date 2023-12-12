package com.dannyhromau.quote.service;

import com.dannyhromau.quote.core.base.BaseService;
import com.dannyhromau.quote.model.Quote;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface QuoteService extends BaseService<Quote> {
    @Override
    List<Quote> getAll(Pageable pageable);

    @Override
    Quote getById(UUID id);

    @Override
    Quote add(Quote entity);

    @Override
    UUID deleteById(UUID id);

    @Override
    Quote update(Quote entity);
    Quote validateId(UUID id);
}
