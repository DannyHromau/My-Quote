package com.dannyhromau.quote.mapper;

import com.dannyhromau.quote.api.dto.QuoteDto;
import com.dannyhromau.quote.model.Quote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuoteMapper {
    @Mapping(target = "id", ignore = true)
    Quote mapToQuote(QuoteDto quoteDto);

    QuoteDto mapToQuote(Quote quote);


    void updateQuoteFromDto(QuoteDto quoteDto, @MappingTarget Quote quote);

    List<QuoteDto> mapToListQuoteDto(List<Quote> quotes);
}
