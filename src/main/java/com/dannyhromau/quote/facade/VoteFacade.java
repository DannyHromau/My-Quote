package com.dannyhromau.quote.facade;

import com.dannyhromau.quote.api.dto.VoteDto;
import com.dannyhromau.quote.core.base.BaseFacade;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface VoteFacade extends BaseFacade<VoteDto> {
    @Override
    List<VoteDto> getDtos(Pageable pageable);

    @Override
    VoteDto getDtoByID(UUID id);

    @Override
    VoteDto addDto(VoteDto dto);

    @Override
    UUID deleteDtoById(UUID id);

    @Override
    VoteDto updateDto(VoteDto dto);
}
