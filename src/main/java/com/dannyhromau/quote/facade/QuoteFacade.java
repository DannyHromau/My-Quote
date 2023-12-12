package com.dannyhromau.quote.facade;

import com.dannyhromau.quote.api.dto.QuoteDto;
import com.dannyhromau.quote.core.base.BaseFacade;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface QuoteFacade extends BaseFacade<QuoteDto> {
    @Override
    List<QuoteDto> getDtos(Pageable pageable);

    @Override
    QuoteDto getDtoByID(UUID id);

    @Override
    QuoteDto addDto(QuoteDto dto);

    @Override
    UUID deleteDtoById(UUID id);

    @Override
    QuoteDto updateDto(QuoteDto dto);
}
