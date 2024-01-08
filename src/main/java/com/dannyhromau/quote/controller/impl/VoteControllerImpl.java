package com.dannyhromau.quote.controller.impl;

import com.dannyhromau.quote.api.dto.VoteDto;
import com.dannyhromau.quote.controller.VoteController;
import com.dannyhromau.quote.facade.VoteFacade;
import com.dannyhromau.quote.service.impl.VoteServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
public class VoteControllerImpl implements VoteController {

    private final VoteFacade voteFacade;

    @Override
    public ResponseEntity<VoteDto> getById(@NonNull UUID id) {
        log.info("call getById in vote with id: {}", id);
        return ResponseEntity.ok(voteFacade.getDtoByID(id));
    }

    @Override
    public ResponseEntity<List<VoteDto>> getAll(Pageable page) {
        log.info("call get all votes");
        return ResponseEntity.ok(voteFacade.getDtos(page));
    }

    @Override
    public ResponseEntity<VoteDto> create(@NonNull @RequestBody VoteDto dto) {
        log.info("call add vote: {}, vote", dto);
        return ResponseEntity.ok(voteFacade.addDto(dto));
    }

    @Override
    public ResponseEntity<VoteDto> update(@NonNull @RequestBody VoteDto dto) {
        log.info("call update in vote: {}, vote", dto);
        return ResponseEntity.ok(voteFacade.updateDto(dto));
    }

    @Override
    public ResponseEntity<UUID> deleteById(@NonNull UUID id) {
        log.info("call delete in vote with id: {}", id);
        return ResponseEntity.ok(voteFacade.deleteDtoById(id));
    }
}
