package com.dannyhromau.quote.facade.impl;

import com.dannyhromau.quote.api.dto.VoteDto;
import com.dannyhromau.quote.facade.VoteFacade;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class VoteFacadeImpl implements VoteFacade {
    @Override
    public List<VoteDto> getDtos(Pageable pageable) {
        return null;
    }

    @Override
    public VoteDto getDtoByID(UUID id) {
        return null;
    }

    @Override
    public VoteDto addDto(VoteDto dto) {
        return null;
    }

    @Override
    public UUID deleteDtoById(UUID id) {
        return null;
    }

    @Override
    public VoteDto updateDto(VoteDto dto) {
        return null;
    }
}
