package com.dannyhromau.quote.controller.impl;

import com.dannyhromau.quote.api.dto.QuoteDto;
import com.dannyhromau.quote.controller.QuoteController;
import com.dannyhromau.quote.facade.QuoteFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
public class QuoteControllerImpl implements QuoteController {

    private final QuoteFacade quoteFacade;

    @Override
    public ResponseEntity<QuoteDto> getById(@NonNull UUID id) {
        log.info("call getById in quote with id: {}", id);
        return ResponseEntity.ok(quoteFacade.getDtoByID(id));
    }

    @Override
    public ResponseEntity<List<QuoteDto>> getAll(Pageable page) {
        log.info("call get all quotes");
        return ResponseEntity.ok(quoteFacade.getDtos(page));
    }

    @Override
    public ResponseEntity<QuoteDto> create(@NonNull @RequestBody QuoteDto dto) {
        log.info("call create in quote: {}, quote", dto);
        return ResponseEntity.ok(quoteFacade.addDto(dto));
    }

    @Override
    public ResponseEntity<QuoteDto> update(@NonNull @RequestBody QuoteDto dto) {
        log.info("call update in quote: {}, quote", dto);
        return ResponseEntity.ok(quoteFacade.updateDto(dto));
    }

    @Override
    public ResponseEntity<UUID> deleteById(@NonNull UUID id) {
        log.info("call delete in quote with id: {}", id);
        return ResponseEntity.ok(quoteFacade.deleteDtoById(id));
    }
}
