package com.dannyhromau.quote.facade.impl;

import com.dannyhromau.quote.api.dto.QuoteDto;
import com.dannyhromau.quote.facade.QuoteFacade;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class QuoteFacadeImpl implements QuoteFacade {
    @Override
    public List<QuoteDto> getDtos(Pageable pageable) {
        return null;
    }

    @Override
    public QuoteDto getDtoByID(UUID id) {
        return null;
    }

    @Override
    public QuoteDto addDto(QuoteDto dto) {
        return null;
    }

    @Override
    public UUID deleteDtoById(UUID id) {
        return null;
    }

    @Override
    public QuoteDto updateDto(QuoteDto dto) {
        return null;
    }
}
