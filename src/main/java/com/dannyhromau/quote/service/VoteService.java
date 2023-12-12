package com.dannyhromau.quote.service;

import com.dannyhromau.quote.core.base.BaseService;
import com.dannyhromau.quote.model.Vote;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface VoteService extends BaseService<Vote> {
    @Override
    List<Vote> getAll(Pageable pageable);

    @Override
    Vote getById(UUID id);

    @Override
    Vote add(Vote entity);

    @Override
    UUID deleteById(UUID id);

    @Override
    Vote update(Vote entity);
    Vote validateId(UUID id);
}
