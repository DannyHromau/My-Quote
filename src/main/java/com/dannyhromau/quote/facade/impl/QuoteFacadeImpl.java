package com.dannyhromau.quote.facade.impl;

import com.dannyhromau.quote.api.dto.QuoteDto;
import com.dannyhromau.quote.facade.QuoteFacade;
import com.dannyhromau.quote.mapper.QuoteMapper;
import com.dannyhromau.quote.model.Quote;
import com.dannyhromau.quote.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class QuoteFacadeImpl implements QuoteFacade {
    private final QuoteService quoteService;
    private final QuoteMapper quoteMapper;
    @Override
    public List<QuoteDto> getDtos(Pageable pageable) {
        return quoteMapper.mapToListQuoteDto(quoteService.getAll(pageable));
    }

    @Override
    public QuoteDto getDtoByID(UUID id) {
        return quoteMapper.mapToQuoteDto(quoteService.getById(id));
    }

    @Override
    public QuoteDto addDto(QuoteDto dto) {
        Quote quote = quoteMapper.mapToQuote(dto);
        return quoteMapper.mapToQuoteDto(quoteService.add(quote));
    }

    @Override
    public UUID deleteDtoById(UUID id) {
        return quoteService.deleteById(id);
    }

    @Override
    public QuoteDto updateDto(QuoteDto dto) {
        Quote quote = quoteService.getById(dto.getId());
        quoteMapper.updateQuoteFromDto(dto, quote);
        return quoteMapper.mapToQuoteDto(quote);
    }
}
