package com.dannyhromau.quote.facade.impl;

import com.dannyhromau.quote.api.dto.VoteDto;
import com.dannyhromau.quote.facade.VoteFacade;
import com.dannyhromau.quote.mapper.VoteMapper;
import com.dannyhromau.quote.model.Quote;
import com.dannyhromau.quote.model.Vote;
import com.dannyhromau.quote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VoteFacadeImpl implements VoteFacade {
    private final VoteService voteService;
    private final VoteMapper voteMapper;

    @Override
    public List<VoteDto> getDtos(Pageable pageable) {
        return voteMapper.mapToListVoteDto(voteService.getAll(pageable));
    }

    @Override
    public VoteDto getDtoByID(UUID id) {
        return voteMapper.mapToVoteDto(voteService.getById(id));
    }

    @Override
    public VoteDto addDto(VoteDto dto) {
        Vote vote = voteService.add(voteMapper.mapToVote(dto));
        return voteMapper.mapToVoteDto(vote);
    }

    @Override
    public UUID deleteDtoById(UUID id) {
        return voteService.deleteById(id);
    }

    @Override
    public VoteDto updateDto(VoteDto dto) {
        Vote vote = voteService.getById(dto.getId());
        voteMapper.updateVoteFromDto(dto, vote);
        return voteMapper.mapToVoteDto(vote);
    }
}
